This example demonstrates a space and a mirror using PostgreSQL.

It is based on the example that is generated when using the XAP plugin from previous versions.

mvn xap:create -Dtemplate=persistent-event-processing


Postgresql installation steps:

1)** Ubuntu Only **
Import of the GPG key for PostgreSQL packages
sudo apt-get install wget ca-certificates
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
add repository to the system
sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ `lsb_release -cs`-pgdg main" >> /etc/apt/sources.list.d/pgdg.list'

2)Install PostgreSQL
sudo apt-get update
sudo apt-get install postgresql postgresql-contrib # installs 10, plus contrib which additional features


3)Creating required user and database for mirror example 
sudo su - postgres
psql
CREATE ROLE scott WITH LOGIN CREATEDB ENCRYPTED PASSWORD 'tiger';
CREATE DATABASE mydb;

4)DB commands cheat sheet

list databases
\l
connect to database
\dt\c mydb
to list tables in database
\dt
check database
SELECT * FROM person;


4)Start service grid (XAP runtime environment)
export GS_NIC_ADDRESS="localhost"
export GS_MANAGER_SERVERS="localhost"

./gs.sh host run-agent --manager --gsc=5

5)Deployment

You will need to run mvn clean package at the project parent.

This will create space/target/space.jar and mirror/target/mirror.jar files. You will need to deploy those in XAP.

To deploy, you can use the webui or the command line.

From command line:
The SLA for the space deployment should be overridden. It should be 2,1.
For space:
./gs.sh pu deploy --partitions=2 --ha <pu name> <path to space.jar>

For mirror:
The mirror is stateless. You don't need to specify any instances (partitions) or backups.
./gs.sh pu deploy <pu name> <path to mirror.jar>

6) You can run the feeder to write objects to the space. They will be replicated to the database

