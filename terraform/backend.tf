terraform {
  backend "s3" {
    bucket         = "kraftor-sf"
    region         = "ap-south-1"
    key            = "daksh/terraform.tfstate"
    dynamodb_table = "terraform-lock"
  }
}
