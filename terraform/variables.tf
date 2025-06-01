variable "aws_region" {
  description = "AWS region to deploy resources"
  default     = "us-east-1"
}

variable "app_image" {
  description = "Docker image to deploy"
  type        = string
}
