set -a
source local.env.prop
set +a
docker-compose --project-name foodcart-infra up
