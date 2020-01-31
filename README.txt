
GIT Repository:

	https://github.com/sergiofilhostark/homework-freelance4j.git
	
	Clone:
		git clone https://github.com/sergiofilhostark/homework-freelance4j.git

PROJECT-SERVICE

	
	Inside project-service directory (cd project-service)

	Namespace:
		oc new-project ssantos-freelance4j-project

	MongoDB:
		oc process -f etc/freelance4j-project-mongodb-persistent.yaml -p PROJECT_DB_USERNAME=mongo -p PROJECT_DB_PASSWORD=mongo -n ssantos-freelance4j-project | oc create -f - -n ssantos-freelance4j-project

	Configmap:
		oc create configmap app-config --from-file=etc/app-config.yaml -n ssantos-freelance4j-project


	Policy:
		oc adm policy add-role-to-group view system:serviceaccounts:ssantos-freelance4j-project -n ssantos-freelance4j-project

	Build + Deploy

		mvn clean fabric8:deploy -Popenshift -Dfabric8.namespace=ssantos-freelance4j-project

	Tests:

		curl -X GET http://project-service-ssantos-freelance4j-project.apps.na311.openshift.opentlc.com/projects

		curl -X GET http://project-service-ssantos-freelance4j-project.apps.na311.openshift.opentlc.com/projects/555

		curl -X GET http://project-service-ssantos-freelance4j-project.apps.na311.openshift.opentlc.com/projects/status/open



FREELANCER-SERVICE

	Inside freelancer-service directory (cd freelancer-service)

	Namespace:
		oc new-project ssantos-freelance4j-freelancer

	PostgreSql:
		oc process -f etc/freelancer-postgresql-persistent.yml -p FREELANCER_DB_USERNAME=jboss -p FREELANCER_DB_PASSWORD=jboss | oc create -f - -n ssantos-freelance4j-freelancer

	Configmap:
		oc create configmap freelancer-service -n ssantos-freelance4j-freelancer --from-literal=spring.datasource.driver-class-name="org.postgresql.Driver" --from-literal=spring.datasource.url="jdbc:postgresql://freelancer-postgresql:5432/freelancerdb" --from-literal=spring.datasource.username="jboss"  --from-literal=spring.datasource.password="jboss"  --from-literal=spring.jpa.properties.hibernate.dialect="org.hibernate.dialect.PostgreSQLDialect"   --from-literal=spring.jpa.hibernate.ddl-auto="update" 

	Policy:
		oc adm policy add-role-to-group view system:serviceaccounts:ssantos-freelance4j-freelancer -n ssantos-freelance4j-freelancer

	Build + Deploy
		mvn clean package fabric8:deploy -Popenshift -Dfabric8.namespace=ssantos-freelance4j-freelancer

	Tests:
		curl -X GET http://freelancer-service-ssantos-freelance4j-freelancer.apps.na311.openshift.opentlc.com/freelancers
		
		curl -X GET http://freelancer-service-ssantos-freelance4j-freelancer.apps.na311.openshift.opentlc.com/freelancers/111


GATEWAY-SERVICE

	Inside gateway-service directory (cd gateway-service)

	Namespace:
		oc new-project ssantos-freelance4j-gateway

	Configmap:
		oc create configmap app-config --from-file=etc/project-defaults.yml -n ssantos-freelance4j-gateway


	Build + Deploy
		mvn clean package fabric8:deploy -Popenshift -Dfabric8.namespace=ssantos-freelance4j-gateway

	Tests:
		curl -X GET http://gateway-service-ssantos-freelance4j-gateway.apps.na311.openshift.opentlc.com/gateway/projects

		curl -X GET http://gateway-service-ssantos-freelance4j-gateway.apps.na311.openshift.opentlc.com/gateway/projects/555

		curl -X GET http://gateway-service-ssantos-freelance4j-gateway.apps.na311.openshift.opentlc.com/gateway/projects/open

		curl -X GET http://gateway-service-ssantos-freelance4j-gateway.apps.na311.openshift.opentlc.com/gateway/freelancers

		curl -X GET http://gateway-service-ssantos-freelance4j-gateway.apps.na311.openshift.opentlc.com/gateway/freelancers/111

