file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala
### scala.reflect.internal.FatalError: 
  ThisType(method findById) for sym which is not a class
     while compiling: file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.12
    compiler version: version 2.13.12
  reconstructed args: -classpath <WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-jJ3U2w0UR5CMrzCPVeswXQ==:<HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.10.4/semanticdb-javac-0.10.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-server_2.13/0.23.24/http4s-ember-server_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-client_2.13/0.23.24/http4s-ember-client_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-dsl_2.13/0.23.24/http4s-dsl_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-circe_2.13/0.23.24/http4s-circe_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-generic_2.13/0.14.6/circe-generic_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-parser_2.13/0.14.6/circe-parser_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect_2.13/3.5.2/cats-effect_2.13-3.5.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-core_2.13/1.0.0-RC4/doobie-core_2.13-1.0.0-RC4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-hikari_2.13/1.0.0-RC4/doobie-hikari_2.13-1.0.0-RC4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-specs2_2.13/1.0.0-RC4/doobie-specs2_2.13-1.0.0-RC4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig_2.13/0.17.4/pureconfig_2.13-0.17.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-core_2.13/0.23.24/http4s-ember-core_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-server_2.13/0.23.24/http4s-server_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/log4cats-slf4j_2.13/2.6.0/log4cats-slf4j_2.13-2.6.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-client_2.13/0.23.24/http4s-client_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/keypool_2.13/0.4.8/keypool_2.13-0.4.8.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-core_2.13/0.23.24/http4s-core_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-jawn_2.13/0.23.24/http4s-jawn_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-core_2.13/0.14.6/circe-core_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-jawn_2.13/0.14.6/circe-jawn_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/chuusai/shapeless_2.13/2.3.10/shapeless_2.13-2.3.10.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect-kernel_2.13/3.5.2/cats-effect-kernel_2.13-3.5.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect-std_2.13/3.5.2/cats-effect-std_2.13-3.5.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-free_2.13/1.0.0-RC4/doobie-free_2.13-1.0.0-RC4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/typename_2.13/1.1.0/typename_2.13-1.1.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/zaxxer/HikariCP/5.0.1/HikariCP-5.0.1.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-core_2.13/4.20.0/specs2-core_2.13-4.20.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-core_2.13/0.17.4/pureconfig-core_2.13-0.17.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-generic_2.13/0.17.4/pureconfig-generic_2.13-0.17.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/log4cats-core_2.13/2.6.0/log4cats-core_2.13-2.6.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/twitter/hpack/1.0.2/hpack-1.0.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-core_2.13/2.10.0/cats-core_2.13-2.10.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/case-insensitive_2.13/1.4.0/case-insensitive_2.13-1.4.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-parse_2.13/1.0.0/cats-parse_2.13-1.0.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-crypto_2.13/0.2.4/http4s-crypto_2.13-0.2.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/co/fs2/fs2-core_2.13/3.9.3/fs2-core_2.13-3.9.3.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/co/fs2/fs2-io_2.13/3.9.3/fs2-io_2.13-3.9.3.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/comcast/ip4s-core_2.13/3.4.0/ip4s-core_2.13-3.4.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/literally_2.13/1.1.0/literally_2.13-1.1.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scodec/scodec-bits_2.13/1.1.38/scodec-bits_2.13-1.1.38.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/vault_2.13/3.5.0/vault_2.13-3.5.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/log4s/log4s_2.13/1.10.0/log4s_2.13-1.10.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/jawn-fs2_2.13/2.4.0/jawn-fs2_2.13-2.4.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/jawn-parser_2.13/1.5.1/jawn-parser_2.13-1.5.1.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-numbers_2.13/0.14.6/circe-numbers_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-free_2.13/2.9.0/cats-free_2.13-2.9.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-matcher_2.13/4.20.0/specs2-matcher_2.13-4.20.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-common_2.13/4.20.0/specs2-common_2.13-4.20.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-sbt/test-interface/1.0/test-interface-1.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/portable-scala/portable-scala-reflect_2.13/1.1.1/portable-scala-reflect_2.13-1.1.1.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.21.9/protobuf-java-3.21.9.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.2/config-1.4.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-generic-base_2.13/0.17.4/pureconfig-generic-base_2.13-0.17.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-kernel_2.13/2.10.0/cats-kernel_2.13-2.10.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-fp_2.13/4.20.0/specs2-fp_2.13-4.20.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/1.1.2/scala-parser-combinators_2.13-1.1.2.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: Select(Select(This(object pos), Pos), instance)
       tree position: line 30 of file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala
            tree tpe: doobie.util.pos.Pos
              symbol: implicit macro method instance in trait PosPlatform
   symbol definition: implicit def instance: doobie.util.pos.Pos (a MethodSymbol)
      symbol package: doobie.util
       symbol owners: macro method instance -> trait PosPlatform
           call site: <none> in <none>

== Source file context for tree position ==

    27     }
    28 
    29     def findBySlug(slug: String): IO[Option[Product]] = {
    30       sql"""
    31         SELECT id, name, slug, description, status, created_at, updated_at, deleted_at 
    32         FROM products 
    33         WHERE id = $_CURSOR_d AND deleted_at IS NULL

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.13.12
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-jJ3U2w0UR5CMrzCPVeswXQ== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.10.4/semanticdb-javac-0.10.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-server_2.13/0.23.24/http4s-ember-server_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-client_2.13/0.23.24/http4s-ember-client_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-dsl_2.13/0.23.24/http4s-dsl_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-circe_2.13/0.23.24/http4s-circe_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-generic_2.13/0.14.6/circe-generic_2.13-0.14.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-parser_2.13/0.14.6/circe-parser_2.13-0.14.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect_2.13/3.5.2/cats-effect_2.13-3.5.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-core_2.13/1.0.0-RC4/doobie-core_2.13-1.0.0-RC4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-hikari_2.13/1.0.0-RC4/doobie-hikari_2.13-1.0.0-RC4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-specs2_2.13/1.0.0-RC4/doobie-specs2_2.13-1.0.0-RC4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig_2.13/0.17.4/pureconfig_2.13-0.17.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-core_2.13/0.23.24/http4s-ember-core_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-server_2.13/0.23.24/http4s-server_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/log4cats-slf4j_2.13/2.6.0/log4cats-slf4j_2.13-2.6.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-client_2.13/0.23.24/http4s-client_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/keypool_2.13/0.4.8/keypool_2.13-0.4.8.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-core_2.13/0.23.24/http4s-core_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-jawn_2.13/0.23.24/http4s-jawn_2.13-0.23.24.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-core_2.13/0.14.6/circe-core_2.13-0.14.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-jawn_2.13/0.14.6/circe-jawn_2.13-0.14.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/chuusai/shapeless_2.13/2.3.10/shapeless_2.13-2.3.10.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect-kernel_2.13/3.5.2/cats-effect-kernel_2.13-3.5.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect-std_2.13/3.5.2/cats-effect-std_2.13-3.5.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-free_2.13/1.0.0-RC4/doobie-free_2.13-1.0.0-RC4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/typename_2.13/1.1.0/typename_2.13-1.1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/zaxxer/HikariCP/5.0.1/HikariCP-5.0.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-core_2.13/4.20.0/specs2-core_2.13-4.20.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-core_2.13/0.17.4/pureconfig-core_2.13-0.17.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-generic_2.13/0.17.4/pureconfig-generic_2.13-0.17.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/log4cats-core_2.13/2.6.0/log4cats-core_2.13-2.6.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/twitter/hpack/1.0.2/hpack-1.0.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-core_2.13/2.10.0/cats-core_2.13-2.10.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/case-insensitive_2.13/1.4.0/case-insensitive_2.13-1.4.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-parse_2.13/1.0.0/cats-parse_2.13-1.0.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-crypto_2.13/0.2.4/http4s-crypto_2.13-0.2.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/co/fs2/fs2-core_2.13/3.9.3/fs2-core_2.13-3.9.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/co/fs2/fs2-io_2.13/3.9.3/fs2-io_2.13-3.9.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/comcast/ip4s-core_2.13/3.4.0/ip4s-core_2.13-3.4.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/literally_2.13/1.1.0/literally_2.13-1.1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scodec/scodec-bits_2.13/1.1.38/scodec-bits_2.13-1.1.38.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/vault_2.13/3.5.0/vault_2.13-3.5.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/log4s/log4s_2.13/1.10.0/log4s_2.13-1.10.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/jawn-fs2_2.13/2.4.0/jawn-fs2_2.13-2.4.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/jawn-parser_2.13/1.5.1/jawn-parser_2.13-1.5.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-numbers_2.13/0.14.6/circe-numbers_2.13-0.14.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-free_2.13/2.9.0/cats-free_2.13-2.9.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-matcher_2.13/4.20.0/specs2-matcher_2.13-4.20.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-common_2.13/4.20.0/specs2-common_2.13-4.20.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-sbt/test-interface/1.0/test-interface-1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/portable-scala/portable-scala-reflect_2.13/1.1.1/portable-scala-reflect_2.13-1.1.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.21.9/protobuf-java-3.21.9.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.2/config-1.4.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-generic-base_2.13/0.17.4/pureconfig-generic-base_2.13-0.17.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-kernel_2.13/2.10.0/cats-kernel_2.13-2.10.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-fp_2.13/4.20.0/specs2-fp_2.13-4.20.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/1.1.2/scala-parser-combinators_2.13-1.1.2.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb


action parameters:
offset: 1027
uri: file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala
text:
```scala
package repository

import cats.effect.IO
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import model.Product
import model.Product._ // Importa las instancias implícitas

trait ProductRepository {
  def findById(id: Int): IO[Option[Product]]
  def findBySlug(slug: String): IO[Option[Product]]
  def create(product: Product): IO[Unit]
  def update(product: Product): IO[Unit]
  def delete(id: Int): IO[Unit]
}

object ProductRepository {
  def apply(xa: Transactor[IO]): ProductRepository = new ProductRepository {

    def findById(id: Int): IO[Option[Product]] = {
      sql"""
        SELECT id, name, slug, description, status, created_at, updated_at, deleted_at 
        FROM products 
        WHERE id = $id AND deleted_at IS NULL
      """.query[Product].option.transact(xa)
    }

    def findBySlug(slug: String): IO[Option[Product]] = {
      sql"""
        SELECT id, name, slug, description, status, created_at, updated_at, deleted_at 
        FROM products 
        WHERE id = $@@d AND deleted_at IS NULL
      """.query[Product].option.transact(xa)
    }

    def create(product: Product): IO[Unit] = {
      sql"""
        INSERT INTO products (name, slug, description, status)
        VALUES (${product.name}, ${product.slug}, ${product.description}, ${product.status})
      """.update.run.transact(xa).void
    }

    def update(product: Product): IO[Unit] = {
      sql"""
        UPDATE products
        SET name = ${product.name}, 
            slug = ${product.slug}, 
            description = ${product.description}, 
            status = ${product.status},
            updated_at = NOW()
        WHERE id = ${product.id}
      """.update.run.transact(xa).void
    }

    def delete(id: Int): IO[Unit] = {
      sql"UPDATE products SET deleted_at = NOW() WHERE id = $id".update.run
        .transact(xa)
        .void
    }
  }
}

```



#### Error stacktrace:

```
scala.reflect.internal.Reporting.abort(Reporting.scala:70)
	scala.reflect.internal.Reporting.abort$(Reporting.scala:66)
	scala.reflect.internal.SymbolTable.abort(SymbolTable.scala:28)
	scala.reflect.internal.Types$ThisType.<init>(Types.scala:1394)
	scala.reflect.internal.Types$UniqueThisType.<init>(Types.scala:1414)
	scala.reflect.internal.Types$ThisType$.apply(Types.scala:1418)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$autoImports$3.applyOrElse(AutoImportsProvider.scala:75)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$autoImports$3.applyOrElse(AutoImportsProvider.scala:60)
	scala.collection.immutable.List.collect(List.scala:267)
	scala.meta.internal.pc.AutoImportsProvider.autoImports(AutoImportsProvider.scala:60)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$autoImports$1(ScalaPresentationCompiler.scala:384)
```
#### Short summary: 

scala.reflect.internal.FatalError: 
  ThisType(method findById) for sym which is not a class
     while compiling: file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.12
    compiler version: version 2.13.12
  reconstructed args: -classpath <WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-jJ3U2w0UR5CMrzCPVeswXQ==:<HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.10.4/semanticdb-javac-0.10.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-server_2.13/0.23.24/http4s-ember-server_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-client_2.13/0.23.24/http4s-ember-client_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-dsl_2.13/0.23.24/http4s-dsl_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-circe_2.13/0.23.24/http4s-circe_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-generic_2.13/0.14.6/circe-generic_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-parser_2.13/0.14.6/circe-parser_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect_2.13/3.5.2/cats-effect_2.13-3.5.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-core_2.13/1.0.0-RC4/doobie-core_2.13-1.0.0-RC4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-hikari_2.13/1.0.0-RC4/doobie-hikari_2.13-1.0.0-RC4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-specs2_2.13/1.0.0-RC4/doobie-specs2_2.13-1.0.0-RC4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig_2.13/0.17.4/pureconfig_2.13-0.17.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-ember-core_2.13/0.23.24/http4s-ember-core_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-server_2.13/0.23.24/http4s-server_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/log4cats-slf4j_2.13/2.6.0/log4cats-slf4j_2.13-2.6.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-client_2.13/0.23.24/http4s-client_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/keypool_2.13/0.4.8/keypool_2.13-0.4.8.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-core_2.13/0.23.24/http4s-core_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-jawn_2.13/0.23.24/http4s-jawn_2.13-0.23.24.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-core_2.13/0.14.6/circe-core_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-jawn_2.13/0.14.6/circe-jawn_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/chuusai/shapeless_2.13/2.3.10/shapeless_2.13-2.3.10.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect-kernel_2.13/3.5.2/cats-effect-kernel_2.13-3.5.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-effect-std_2.13/3.5.2/cats-effect-std_2.13-3.5.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/doobie-free_2.13/1.0.0-RC4/doobie-free_2.13-1.0.0-RC4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/tpolecat/typename_2.13/1.1.0/typename_2.13-1.1.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/zaxxer/HikariCP/5.0.1/HikariCP-5.0.1.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-core_2.13/4.20.0/specs2-core_2.13-4.20.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-core_2.13/0.17.4/pureconfig-core_2.13-0.17.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-generic_2.13/0.17.4/pureconfig-generic_2.13-0.17.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/log4cats-core_2.13/2.6.0/log4cats-core_2.13-2.6.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/twitter/hpack/1.0.2/hpack-1.0.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-core_2.13/2.10.0/cats-core_2.13-2.10.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/case-insensitive_2.13/1.4.0/case-insensitive_2.13-1.4.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-parse_2.13/1.0.0/cats-parse_2.13-1.0.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/http4s/http4s-crypto_2.13/0.2.4/http4s-crypto_2.13-0.2.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/co/fs2/fs2-core_2.13/3.9.3/fs2-core_2.13-3.9.3.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/co/fs2/fs2-io_2.13/3.9.3/fs2-io_2.13-3.9.3.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/comcast/ip4s-core_2.13/3.4.0/ip4s-core_2.13-3.4.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/literally_2.13/1.1.0/literally_2.13-1.1.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scodec/scodec-bits_2.13/1.1.38/scodec-bits_2.13-1.1.38.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/vault_2.13/3.5.0/vault_2.13-3.5.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/log4s/log4s_2.13/1.10.0/log4s_2.13-1.10.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/jawn-fs2_2.13/2.4.0/jawn-fs2_2.13-2.4.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/jawn-parser_2.13/1.5.1/jawn-parser_2.13-1.5.1.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/circe/circe-numbers_2.13/0.14.6/circe-numbers_2.13-0.14.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-free_2.13/2.9.0/cats-free_2.13-2.9.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-matcher_2.13/4.20.0/specs2-matcher_2.13-4.20.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-common_2.13/4.20.0/specs2-common_2.13-4.20.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-sbt/test-interface/1.0/test-interface-1.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/portable-scala/portable-scala-reflect_2.13/1.1.1/portable-scala-reflect_2.13-1.1.1.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.21.9/protobuf-java-3.21.9.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.2/config-1.4.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/pureconfig/pureconfig-generic-base_2.13/0.17.4/pureconfig-generic-base_2.13-0.17.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/typelevel/cats-kernel_2.13/2.10.0/cats-kernel_2.13-2.10.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/specs2/specs2-fp_2.13/4.20.0/specs2-fp_2.13-4.20.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/1.1.2/scala-parser-combinators_2.13-1.1.2.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: Select(Select(This(object pos), Pos), instance)
       tree position: line 30 of file://<WORKSPACE>/src/main/scala/repository/ProductRepository.scala
            tree tpe: doobie.util.pos.Pos
              symbol: implicit macro method instance in trait PosPlatform
   symbol definition: implicit def instance: doobie.util.pos.Pos (a MethodSymbol)
      symbol package: doobie.util
       symbol owners: macro method instance -> trait PosPlatform
           call site: <none> in <none>

== Source file context for tree position ==

    27     }
    28 
    29     def findBySlug(slug: String): IO[Option[Product]] = {
    30       sql"""
    31         SELECT id, name, slug, description, status, created_at, updated_at, deleted_at 
    32         FROM products 
    33         WHERE id = $_CURSOR_d AND deleted_at IS NULL