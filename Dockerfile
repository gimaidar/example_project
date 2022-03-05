FROM openjdk

WORKDIR /exampleApp

COPY . .

#порт
EXPOSE 3000

CMD["openjdk", ""]