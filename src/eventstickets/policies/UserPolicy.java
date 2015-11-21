package eventstickets.policies;

import eventstickets.models.Role;
import eventstickets.models.User;

public class UserPolicy {
	public static boolean index(User user) {
		return user.getRole() == Role.ADMIN;
	}
}
