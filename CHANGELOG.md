# Change Log
All notable changes to this project will be documented in this file, which loosely follows the guidelines on [Keep a CHANGELOG](http://keepachangelog.com/).

This project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]

## [25.104.0] - 2026-09-07
First official (non-milestone) release of the Java 25 / WildFly 40 / Jakarta EE 11 line,
consolidating milestones `25.104.0-M1` to `25.104.0-M8`.

### Changed
- Upgraded to Java 25 / WildFly 40 / Jakarta EE 11 (`25.104.x` release line)
- Bumped parent `maven-framework-parent-pom` and `maven-common-bom.version` to the released `25.104.0` — Java 25 / Jakarta EE 11 targeting (`java.major.version=25`, `enforcer.java.version.range=[25,)`), WildFly `40.0.0.Final`, Weld 6, RESTEasy 7, Hibernate ORM 6, Apache Artemis `2.54.0` under the new `org.apache.artemis` groupId, `liquibase.version=5.0.3`, Jackson `2.21.5` (**CVE-2026-54515**) and the `org.junit:junit-bom` import
- Migrated all `javax.*` imports to `jakarta.*` across all modules
- Updated CDI `beans.xml` descriptors from version `1.1` to `4.0` (`beans_4_0.xsd`) in all modules
- Removed `org.glassfish:javax.json` dependency; replaced with `org.glassfish:jakarta.json` where needed
- Added `-Dnet.bytebuddy.experimental=true --add-opens java.base/java.lang=ALL-UNNAMED` to the `maven-failsafe-plugin` `argLine` in the `integration-tests` profile — required for ByteBuddy and reflective access under Java 25
- Pinned `jakarta.xml.bind-api` to `2.3.2` in the `coveralls-maven-plugin` `<dependencies>` block via the new `jakarta.xml.bind-api.raml.version` property; `coveralls-maven-plugin:4.3.0` ships with `2.3.1`, which is unavailable in the CI repository

### Removed
- Local `java.major.version` (`17`), `compiler.*`, `plugins.maven.compiler.version`, `jaxb.version` and `plugins.coveralls.version` overrides from the root pom — these now come from `maven-framework-parent-pom`, so the project no longer holds Java 17 settings back
- Local `plugins.maven.failsafe.version` (`3.1.2`) override — the value now comes from `maven-parent-pom`, moving integration tests onto failsafe `3.5.6`. The stale local pin was silently holding back the Maven 3.9.16 default and risked the Surefire/Failsafe test-discovery drift that the `junit-bom` import in `cp-maven-common-bom` exists to prevent
- Local `jakarta.xml.bind-api.raml.version` (`2.3.2`) override — the value now comes from `maven-framework-parent-pom`, which centralised it so child projects could drop their copies
- `liquibase.hub.mode: off` from `file-service-liquibase/src/main/resources/liquibase.properties` — the key was removed in Liquibase 4.12.0 and any JAR bundling 4.12.0 or later rejects it under strict checking and exits non-zero, causing the Kubernetes pre-install Liquibase job to time out at 900s

### Fixed
- `MetadataJdbcRepository`: replaced stale `javax.json` static imports; uses `JsonObjects.jsonReaderFactory` (shared factory instance — `Json.createReaderFactory` is expensive and must not be called per-invocation)
- `FileServiceTestClient`: replaced `jsonBuilderFactory.createObjectBuilder()` with the shared factory from `JsonObjects` for the same reason
- `AlfrescoRestClientTest`: migrated all `javax.ws.rs.*` imports (including static import) to `jakarta.ws.rs.*`
- Removed redundant explicit `jakarta.enterprise:jakarta.cdi-api` dependency from `file-alfresco` — already provided transitively by `jakarta.jakartaee-api`

## [17.104.0-M3] - 2025-10-27
### Changed
- Correctly organise AlfrescoRestClient to utilise client lifecycle. 

## [17.104.0-M2] - 2025-10-10
### Changed
- Used JsonFactory instead of Json.create methods as per https://github.com/jakartaee/jsonp-api/issues/154

## [17.104.0-M1] - 2025-07-29
### Changed
- Updated version to 17.104.x for the new framework E
### Security
- Updated to latest common-bom for latest third party security fixes:
  - Update commons.beanutils version to **1.11.0** to fix **security vulnerability CVE-2025-48734**
    Detail: https://cwe.mitre.org/data/definitions/284.html
  - Update resteasy version to **3.15.5.Final** to fix **security vulnerability CVE-2023-0482**
    Detail: https://cwe.mitre.org/data/definitions/378.html
  - Update classgraph version to **4.8.112** to fix **security vulnerability CVE-2021-47621**
    Detail: https://cwe.mitre.org/data/definitions/611.html
  - Update commons-lang version to **3.18.0** to fix **security vulnerability CVE-2025-48924**
    Detail: https://cwe.mitre.org/data/definitions/674.html

## [17.103.0] - 2025-07-11
### Changed
- Update maven-framework-parent-pom to 17.103.0
- Update maven-common-bom to 17.103.1

## [17.103.0] - 2025-04-29
### Changed
- Extract file service modules from cp-framework-libraries repo

## [11.0.0] - 2023-01-26
### Changed
- Cherry-picked from java 8
  - Changed the file service to make hard delete of files from postgres on delete rather than just marking them as deleted

## [7.0.5] - 2020-05-27
### Added
- Added File Service Alfresco as a sub module
    
