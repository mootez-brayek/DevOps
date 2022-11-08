terraform {
  required_providers {
    docker = {
      source = "kreuzwerker/docker"
      version = "2.23.0"
    }
  }
}

provider "docker" {

}

# Pulls the image
resource "docker_image" "sonarqube" {
  name = "sonarqube:8.9.7-community"

}

resource "docker_image" "nexus3" {
  name = "sonatype/nexus3"
}

#nexus container provision
resource "docker_container" "nexus-container" {
  image = docker_image.nexus3.image_id
  name  = "nexus-container"
  ports {
    internal = 8081
    external = 8081
  }
  volumes {
    volume_name = "nexus-data"
    container_path = "/nexus-data"
  }
}
# sonar container provision
resource "docker_container" "sonarqube-container" {
  image = docker_image.sonarqube.image_id
  name  = "sonarqube-container"
  ports {
    internal = 9000
    external = 9000
  }
  volumes {
    container_path = "/opt/sonarqube/data"
    volume_name = "sonar-data"
  }
  volumes {
    container_path = "/opt/sonarqube/extensions"
    volume_name = "sonar-extensions"
  }
}