Reward And Punishment Backend Code
-----------

### Running via docker
Make sure docker is installed on your machine. 
Run the following commands at the root of your project:

```
docker build -t app .
docker-compose up
```

### Running without docker
If you prefer to setup your env for backend instead of using docker, you should 
have `jdk>=9` and latest version of postgres installed. The code is run with the 
following command: 

```java -jar server backend.yml```

So in your IDE go to edit configuration section and add `server backend` to 
program arguments. In postgres you should have user `ood` with password `ood` who is 
also a super user and can login. Following commands may come in handy.

```
# After entering postgres shell via psql command
CREATE USER ood WITH LOGIN;
ALTER USER ood WITH PASSWORD 'ood'; // Maybe double quotes. not sure
ALTER USER ood WITH SUPERUSRE;
``` 

