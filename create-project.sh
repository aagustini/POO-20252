#!/bin/bash

if [ $# -lt 2 ]; then
  echo "Uso: $0 <groupId> <artifactId>"
  echo "Exemplo: $0 com.exemplo meu-projeto"
  exit 1
fi

GROUP_ID=$1
ARTIFACT_ID=$2

echo "Criando projeto Maven '$ARTIFACT_ID' com groupId '$GROUP_ID' e Java 21..."

BASE_DIR=$ARTIFACT_ID
SRC_MAIN_JAVA="$BASE_DIR/src/main/java/$(echo $GROUP_ID | tr '.' '/')"
SRC_TEST_JAVA="$BASE_DIR/src/test/java/$(echo $GROUP_ID | tr '.' '/')"

mkdir -p "$SRC_MAIN_JAVA"
mkdir -p "$SRC_TEST_JAVA"

cat > "$BASE_DIR/pom.xml" << EOF
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>$GROUP_ID</groupId>
  <artifactId>$ARTIFACT_ID</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.release>21</maven.compiler.release>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <!-- JUnit Jupiter API e Engine para testes -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.10.0</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- Plugin compilador com release -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.11.0</version>
        <configuration>
          <release>21</release>
        </configuration>
      </plugin>

      <!-- Plugin para testes -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.1.2</version>
        <configuration>
          <useModulePath>false</useModulePath>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
EOF

cat > "$SRC_MAIN_JAVA/App.java" << EOF
package $GROUP_ID;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, world! Projeto $ARTIFACT_ID criado com sucesso.");
    }
}
EOF

cat > "$SRC_TEST_JAVA/AppTest.java" << EOF
package $GROUP_ID;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testApp() {
        assertTrue(true, "Teste básico de funcionamento.");
    }
}
EOF

cat > "$BASE_DIR/README.md" << EOF
# $ARTIFACT_ID

Projeto Maven criado automaticamente com Java 21 e JUnit 5.

## Como compilar e rodar

\`\`\`bash
mvn compile
mvn exec:java -Dexec.mainClass="$GROUP_ID.App"
\`\`\`

## Como rodar testes

\`\`\`bash
mvn test
\`\`\`

EOF

echo "Projeto '$ARTIFACT_ID' criado com sucesso com Java 21 e testes configurados."
