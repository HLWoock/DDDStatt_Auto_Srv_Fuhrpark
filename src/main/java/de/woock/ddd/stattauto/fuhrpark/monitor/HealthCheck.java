package de.woock.ddd.stattauto.fuhrpark.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

	@Override
	public Health health() {
		if (check()) {
			return Health.up().withDetail("message", "all up and running").build();
		}
		return Health.outOfService().build();
	}

	private boolean check() {
		return false;
	}

}
