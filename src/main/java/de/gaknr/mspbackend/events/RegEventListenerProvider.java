package de.gaknr.mspbackend.events;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

public class RegEventListenerProvider implements EventListenerProvider {

    private final KeycloakSession session;

    public RegEventListenerProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        if(EventType.REGISTER.equals(event.getType())) {
            System.out.println("HELLO");
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {

    }

    @Override
    public void close() {

    }

}
