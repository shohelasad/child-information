# Run in development profile
    $gradle clean run
     
# Build war in development profile      
    $gradle clean bootRepackage

# Build war in staging profile      
    $gradle -Pstg clean bootRepackage 

# Build war in staging profile      
    $gradle -Pprod clean bootRepackage

    
 Once you have deployed you WAR file on your application server:

It will use by default the "dev" profile
It can run in "production mode" if you trigger the "prod" profile (there are several ways to trigger a Spring profile, 
for example you can add `-Dspring.profiles.active=prod` to your JAVA_OPTS when running your server)

Or, 
Just create file setenv.sh in Tomcat’s bin directory with content:

    JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=prod"