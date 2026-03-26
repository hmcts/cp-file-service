# Change Log
All notable changes to this project will be documented in this file, which loosely follows the guidelines on [Keep a CHANGELOG](http://keepachangelog.com/).

This project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]

## [21.0.0-SNAPSHOT] - 2026-03-26
### Changed
- Updated project version to `21.0.0-SNAPSHOT`
- Updated `maven-framework-parent-pom` to `21.0.0-SNAPSHOT` (Java 21, Jakarta EE 10)
- Updated `maven-common-bom` to `21.0.0-SNAPSHOT` (CDI 4.0.1, Jakarta EE API 10.0.0)
- Updated CDI `beans.xml` descriptors from version `1.1` to `4.0` (`beans_4_0.xsd`) across all modules
- Replaced `org.glassfish:javax.json` dependency with `org.glassfish:jakarta.json` in all modules
- Resolved git merge conflicts in `JsonObjects`, `MetadataJdbcRepository`, `FileServiceTestClient`, `AlfrescoFileSender`, and `AlfrescoFileRemover` — all migrated to `jakarta.*` imports
- Fixed `javax.ws.rs` imports in `AlfrescoRestClientTest` to use `jakarta.ws.rs`
- Updated `openejb.version` in `maven-common-bom` from `8.0.14` to `10.0.0` (TomEE 10 / Jakarta EE 10 / CDI 4.0 support via OpenWebBeans 4.0.3)
- Updated JaCoCo to `0.8.12` in `maven-parent-pom` for Java 21 class file support
- Added `argLine` to maven-failsafe-plugin in integration-tests profile with `-Dnet.bytebuddy.experimental=true --add-opens java.base/java.lang=ALL-UNNAMED` for Mockito compatibility on Java 21

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
    
