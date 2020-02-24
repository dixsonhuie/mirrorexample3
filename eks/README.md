Steps for EKS install


eksctl create cluster \
--name mygiga-central \
--version 1.14 \
--region ca-central-1 \
--nodegroup-name standard-workers \
--node-type t3.medium \
--nodes 8 \
--nodes-min 8 \
--nodes-max 8


kubectl create -f namespace-central.json
kubectl create -f rbac-config.yaml
# initialize helm
helm init --service-account tiller

helm install stable/postgresql --namespace gigaspaces-central-ns

*** Install messages ***
PostgreSQL can be accessed via port 5432 on the following DNS name from within your cluster:

    terrifying-hydra-postgresql.gigaspaces-central-ns.svc.cluster.local - Read/Write connection

To get the password for "postgres" run:

    export POSTGRES_PASSWORD=$(kubectl get secret --namespace gigaspaces-central-ns terrifying-hydra-postgresql -o jsonpath="{.data.postgresql-password}" | base64 --decode)

To connect to your database run the following command:

    kubectl run terrifying-hydra-postgresql-client --rm --tty -i --restart='Never' --namespace gigaspaces-central-ns --image docker.io/bitnami/postgresql:11.6.0-debian-9-r0 --env="PGPASSWORD=$POSTGRES_PASSWORD" --command -- psql --host terrifying-hydra-postgresql -U postgres -d postgres -p 5432



To connect to your database from outside the cluster execute the following commands:

    kubectl port-forward --namespace gigaspaces-central-ns svc/terrifying-hydra-postgresql 5432:5432 &
    PGPASSWORD="$POSTGRES_PASSWORD" psql --host 127.0.0.1 -U postgres -d postgres -p 5432

*** End install messages ***

Postgres password = SmVMcea3OY

# Update the jar file
Modify the dburl of the database in the properties file. The properties file for the mirror pu can be found here: <git proj dir>/mirror/src/main/resources/pu.properties

The properties file for the space pu can be found here:
<git proj dir>/space/src/main/resources/pu.properties

# connect to db (creates a temporary client pod)
kubectl run terrifying-hydra-postgresql-client --rm --tty -i --restart='Never' --namespace gigaspaces-central-ns --image docker.io/bitnami/postgresql:11.6.0-debian-9-r0 --env="PGPASSWORD=$POSTGRES_PASSWORD" --command -- psql --host terrifying-hydra-postgresql -U postgres -d postgres -p 5432

CREATE ROLE scott WITH LOGIN CREATEDB ENCRYPTED PASSWORD 'tiger';
CREATE DATABASE mydb;

# helm manager install
helm install gigaspaces/xap-manager --name mymanager --version=15.0.0 --namespace gigaspaces-central-ns

# helm space install
helm install gigaspaces/xap-pu --name myspace-ca --version=15.0.0 --set manager.name=mymanager,partitions=2,ha=true,antiAffinity.enabled=true,resourceUrl=https://github.com/dixsonhuie/mirrorexample3/blob/master/eks/mirrorexample-space.jar?raw=true,license="Product=XAP;Version=15.0;Type=ENTERPRISE;Customer=GigaSpaces_Technologies_-_Internal_dixson_huie_DEV;Expiration=2020-Mar-20;Hash=VfapNNNsPZXQQjNoqXQR" --set service.lrmi.enabled=true --namespace gigaspaces-central-ns

# helm mirror install
helm install gigaspaces/xap-pu --name mymirror --version=15.0.0 --set manager.name=mymanager,resourceUrl=https://github.com/dixsonhuie/mirrorexample3/blob/master/eks/mirrorexample-mirror.jar?raw=true,license="Product=XAP;Version=15.0;Type=ENTERPRISE;Customer=GigaSpaces_Technologies_-_Internal_dixson_huie_DEV;Expiration=2020-Mar-20;Hash=VfapNNNsPZXQQjNoqXQR" --namespace gigaspaces-central-ns

# get the external ip of the manager
kubectl get services -n gigaspaces-central-ns

# modify the lookup locators and run the feeder

# connect to postgres, verify items have been created
\dt\c mydb;
select * from Person;

# delete space 
helm del --purge myspace-ca

# start space to test initial load

# verify 100 Person objects were reloaded


eksctl delete cluster --name  mygiga-central --region ca-central-1
