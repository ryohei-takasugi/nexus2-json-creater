# Sonatype Nexus 2 から アーティファクト一覧を生成

# メモ
・未完成
・リクエストURLが固定
・Jsonファイルまでは生成していない

# リクエストするURI
```
http://localhost:8081/nexus/service/local/lucene/search?r=releases&g=org.gradle.sample&p=module
http://localhost:8081/nexus/service/local/repositories/releases/content/org/gradle/sample/library/1.1/library-1.1.pom
```

# コンソール出力結果
```
----------------------
Artifact found: org.gradle.sample:library:1.1
org.gradle.sample
library
1.1
module
null
null
----------------------
Artifact found: org.gradle.sample:test-artifact:1.0
org.gradle.sample
test-artifact
1.0
module
aaa
normal
```