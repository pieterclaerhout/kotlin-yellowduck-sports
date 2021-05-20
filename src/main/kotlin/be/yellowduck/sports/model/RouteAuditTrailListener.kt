package be.yellowduck.sports.model

import javax.persistence.*

class RouteAuditTrailListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private fun beforeAnyUpdate(route: Route) {
        if (route.id == 0L) {
            println("[ROUTE AUDIT] About to add a route")
        } else {
            println("[ROUTE AUDIT] About to update/delete route: ${route.id}")
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private fun afterAnyUpdate(route: Route) {
        println("[ROUTE AUDIT] add/update/delete complete for route: ${route.id}")
    }

    @PostLoad
    private fun afterLoad(route: Route) {
        println("[ROUTE AUDIT] route loaded from database: ${route.id}")
    }

}