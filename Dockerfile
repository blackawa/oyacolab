FROM clojure
ADD ./target/uberjar/oyacolab-standalone.jar ./
EXPOSE 3000
CMD [ "java", "-jar", "./oyacolab-standalone.jar" ]
