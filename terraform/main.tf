module "ecr" {
  source  = "terraform-aws-modules/ecr/aws"
  version = "~> 2.2"

  repository_name = "client"
  repository_type = "private"

  repository_image_tag_mutability = "MUTABLE"

  create_lifecycle_policy = false

  # Forcefully delete, even if it contains images
  repository_force_delete = true

  tags = local.common_tags
}
