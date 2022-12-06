rootProject.name = "gradle-kotlin-multi-module-example"

include("untitled-bean")
include("untitled-common")
include("untitled-service")

include("untitled-service:untitled-service-user")
findProject(":untitled-service:untitled-service-user")?.name = "untitled-service-user"

include("untitled-service:untitled-service-order")
findProject(":untitled-service:untitled-service-order")?.name = "untitled-service-order"
include("untitled-gateway")
