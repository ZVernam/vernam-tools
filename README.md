# vernam-tools
![Android Tests](https://github.com/ZVernam/vernam-tools/workflows/Android%20Tests/badge.svg)
![Publish package to GitHub Packages](https://github.com/ZVernam/vernam-tools/workflows/Publish%20package%20to%20GitHub%20Packages/badge.svg)


Vernam Core Library provides all necessary utilities 
for generating cryptic passwords

#### Publishing:
In order to publish the packet to GitHub Package you need to provide `GITHUB_USERNAME` and `GITHUB_TOKEN`.
You may pass it via env variables like this:
```shell script
USERNAME=<USERNAME> TOKEN=<TOKEN> ./gradlew publish
```

Or create file `local.properties` with following content:
```properties
gpr.user=<github username>
gpr.key=<github token>
```

#### Usage:
1. Add `GitHub Package` repo to your Gradle project:
```groovy
def localProperties = new Properties()
localProperties.load(new FileInputStream(rootProject.file("local.properties")))

repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/ZVernam/vernam-tools")
        credentials {
            username = localProperties["gpr.user"] ?: System.getenv("USERNAME")
            password = localProperties["gpr.key"] ?: System.getenv("TOKEN")
        }
    }
}
```
2. Add `implementation` dependency:
```groovy
// vernam library
implementation "com.github.zeckson:vernam-tools:$version"
```