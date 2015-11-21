package eventstickets.policies;

import eventstickets.models.Role;
import eventstickets.models.User;

public class MiniCoursePolicy {
	public static boolean index(User user) {
		return user.getRole() == Role.MANAGER || user.getRole() == Role.ADMIN;
	}
}
