#!/bin/bash

/opt/keycloak/bin/kc.sh build --spi-event-listener-provider=regis --spi-event-listener-regis-enabled=true

/opt/keycloak/bin/kc.sh show-config

/opt/keycloak/bin/kc.sh start-dev --import-realm