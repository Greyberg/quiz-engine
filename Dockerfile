FROM adoptopenjdk/openjdk11:alpine-slim

ENV SBT_VERSION 1.3.0

WORKDIR "/quizzes"

RUN apk add bash curl
RUN  curl -fsL -o sbt-$SBT_VERSION.tgz https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.tgz && \
  tar -xf sbt-$SBT_VERSION.tgz -C /usr/local && \
  ln -s /usr/local/sbt/bin/* /usr/local/bin/

RUN mkdir project
COPY ["build.sbt", "./"]
COPY ["project/assembly.sbt", "project/build.properties", "project/plugins.sbt", "./project/"]
RUN sbt update

COPY [".scalafmt.conf", "./"]
COPY ["app", "app"]
COPY ["conf", "conf"]
COPY ["public", "public"]
RUN sbt assembly


FROM adoptopenjdk/openjdk11:alpine-slim

WORKDIR "/quizzes"

COPY --from=0 /quizzes/target/scala-*/quizzes.jar .
CMD ["java", "-jar", "quizzes.jar"]