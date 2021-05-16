package be.yellowduck.sports.model

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface RouteRepository : CrudRepository<Route, Long> {

    fun findAllByOrderByChangedAtDesc(): Iterable<Route>

    fun findAllBySportOrderByChangedAtDesc(sport: String): Iterable<Route>

    @Query("SELECT new be.yellowduck.sports.model.Summary(sport, count(*) AS count) FROM routes GROUP BY sport ORDER BY sport")
    fun findSportsWithCount(): Iterable<Summary>

    @Query("SELECT count(*) FROM routes WHERE token_id = ?1")
    fun findTotalRouteCount(tokenId: Long): Long

}