#prometheus image
resource "docker_image" "prometheus" {
  name = "prom/prometheus"
}
#grafana image
resource "docker_image" "grafana" {
  name = "grafana/grafana"
}

#prometheus container
resource "docker_container" "prometheus-container" {
  image = docker_image.prometheus.image_id
  name  = "prometheus-container"
  restart = "always"
  ports {
    internal = 9090
    external = 9090
  }
  volumes {
    volume_name = "prometheus-data"
    container_path = "/prometheus"
  }
}
#grafana container
resource "docker_container" "grafana-container" {
  image = docker_image.grafana.image_id
  name  = "grafana-container"
  restart = "always"
  ports {
    internal = 3000
    external = 3000
  }
  depends_on = [docker_container.prometheus-container]
  volumes {
    container_path = "/var/lib/grafana"
    volume_name = "grafana_data"
  }
  volumes {
    container_path = "/etc/grafana/provisioning/"
    host_path = "/grafana/provisioning/"
  }
}
