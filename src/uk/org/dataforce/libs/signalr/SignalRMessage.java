/*
 *  Copyright 2018 Shane Mc Cormack <shanemcc@gmail.com>.
 *  See LICENSE for licensing details.
 */
package uk.org.dataforce.dflibs.signalr;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * APIEvent to represent a message from the API.
 *
 * @author Shane Mc Cormack <shanemcc@gmail.com>
 */
public class SignalRMessage {
    /** Which "Hub" did this event come from? */
    private String hub = "";
    /** What method does the game want us to call? */
    private String method = "";
    /** What arguments did the game provide? */
    private List<Object> args = Collections.emptyList();
    /** What state did the game provide? */
    private Map<String, Object> state = Collections.emptyMap();

    /**
     * Set the Hub from a "hub" key.
     *
     * @param hub The value for the key.
     */
    @JsonProperty("Hub")
    public void setHub(final String hub) {
        this.hub = hub;
    }

    /**
     * Set the Method from a "method" key.
     *
     * @param method The value for the key.
     */
    @JsonProperty("Method")
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * Set the Args from an "args" key.
     *
     * @param args The value for the key.
     */
    @JsonProperty("Args")
    @SuppressWarnings("unchecked")
    public void setArgs(final List<Object> args) {
        this.args = args;
    }

    /**
     * Set the State from a "state" key.
     *
     * @param state The value for the key.
     */
    @JsonProperty("State")
    public void setState(final Map<String, Object> state) {
        this.state = state;
    }

    /**
     * Set the Hub from a "h" key.
     *
     * @param h The value for the key.
     */
    @JsonProperty("H")
    public void setH(final String h) {
        setHub(h);
    }

    /**
     * Set the Method from an "M" key.
     *
     * @param m The value for the key.
     */
    @JsonProperty("M")
    public void setM(final String m) {
        setMethod(m);
    }

    /**
     * Set the Args from an "a" key.
     *
     * @param a The value for the key.
     */
    @JsonProperty("A")
    public void setA(final List<Object> a) {
        setArgs(a);
    }

    /**
     * Arguments for this method.
     *
     * This returns a clone of the arguments for this method.
     *
     * @return Arguments
     */
    public List<Object> getArgs() {
        return args.stream().collect(Collectors.toList());
    }

    /**
     * Which hub provided this message.
     *
     * @return Hub
     */
    public String getHub() {
        return hub;
    }

    /**
     * What method should we call?
     *
     * @return Method name
     */
    public String getMethod() {
        return method;
    }

    /**
     * Get the state returned with this event.
     *
     * @return State.
     */
    public Map<String, Object> getState() {
        return new HashMap<>(state);
    }

    @Override
    public String toString() {
        return method + " from " + getHub();
    }

    /**
     * Check if two different APIMessageEvents appear equal.
     *
     * This checks that our hub/method are the same, and that our args list
     * looks similar (same length, and the maps are the same length and all the
     * values of all the keys in the map compare equally to each other.)
     *
     * @param obj Object to compare us to.
     * @return
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof SignalRMessage) {
            final SignalRMessage aObj = (SignalRMessage)obj;

            if (aObj.hub.equals(hub) && aObj.method.equals(method)) {
                return compareLists(args, aObj.args);
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean compareLists(final List<Object> listA, final List<Object> listB) {
        if (listA.size() == listB.size()) {
            for (int i = 0; i < listA.size(); i++) {

                if (listA.get(i) instanceof Map && listB.get(i) instanceof Map) {
                    if (!compareMaps((Map)listA.get(i), (Map)listB.get(i))) {
                        return false;
                    }
                }

                if (listA.get(i) instanceof List && listB.get(i) instanceof List) {
                    if (!compareLists((List)listA.get(i), (List)listB.get(i))) {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }

    private boolean compareMaps(final Map<String, Object> mapA, final Map<String, Object> mapB) {
        if (mapA.size() != mapB.size()) {
            return false;
        }
        if (!mapA.keySet().stream().noneMatch(key -> !mapA.get(key).equals(mapB.get(key)))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.hub);
        hash = 59 * hash + Objects.hashCode(this.method);
        hash = 59 * hash + Objects.hashCode(this.args);
        hash = 59 * hash + Objects.hashCode(this.state);
        return hash;
    }
}
