# mydom-solution
Sandbox project for learning and testing purposes

Config Server:
curl.exe -k https://start.spring.io/starter.zip `
  -d dependencies=cloud-config-server,actuator `
  -d type=maven-project `
  -d language=java `
  -d bootVersion=3.4.5 `
  -d baseDir=mydom-config-server `
  -d name=mydom-config-server `
  -d groupId=com.mariapuri.mydom `
  -d artifactId=mydom-config-server `
  -d packageName=com.mariapuri.mydom.config `
  -d packaging=jar `
  -d javaVersion=21 `
  -o mydom-config-server.zip


Auth Service
curl https://start.spring.io/starter.zip ^
  -d dependencies=web,security,oauth2-resource-server,config-client,validation ^
  -d type=maven-project ^
  -d language=java ^
  -d bootVersion=3.4.5 ^
  -d baseDir=mydom-auth-service ^
  -d name=mydom-auth-service ^
  -d groupId=com.mariapuri.mydom ^
  -d artifactId=mydom-auth-service ^
  -d packageName=com.mariapuri.mydom.auth ^
  -d packaging=jar ^
  -d javaVersion=21 ^
  -o mydom-auth-service.zip
