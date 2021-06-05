package ru.devhack.motomoto.sportevents.db.meta;

/**
 * Database metadata
 *
 * @author AR
 */
public final class SportEventsDBMeta {
    public static final String schema = "main";

    public static final class user {
        public static final String name = "user";
        
        public static final class fld {
            public static final String id = "id";
            public static final String employee_code = "employee_code";
            public static final String first_name = "first_name";
            public static final String middle_name = "middle_name";
            public static final String last_name = "last_name";
            public static final String image_url = "image_url";
            public static final String role = "role";
            public static final String password = "password";
        }
    }

    public static final class event {
        public static final String name = "event";

        public static final class fld {
            public static final String id = "id";
            public static final String name = "name";
            public static final String event_date = "event_date";
            public static final String registration_over = "registration_over";
            public static final String event_limit = "event_limit";
            public static final String description = "description";
            public static final String address = "address";
            public static final String image_url = "image_url";
        }
    }

    public static final class user_event {
        public static final String name = "user_event";

        public static final class fld {
            public static final String id = "id";
            public static final String participation_type = "participation_type";
            public static final String approved = "approved";
            public static final String event_id = "event_id";
            public static final String user_id = "user_id";
        }
    }

    public static final class user_activity {
        public static final String name = "user_activity";

        public static final class fld {
            public static final String id = "id";
            public static final String user_id = "user_id";
            public static final String type = "type";
            public static final String activity_date = "activity_date";
            public static final String image_url = "image_url";
        }
    }

}
