#!/bin/bash

/opt/keycloak/bin/kc.sh build

/opt/keycloak/bin/kc.sh show-config

/opt/keycloak/bin/kc.sh start-dev --import-realm --spi-event-listener-regis-enabled=true --spi-event-listener-provider=regis