package com.neo.androidpagingurgent.network.model;


import java.util.List;
import java.util.Objects;


public class StackApiResponse {
    public List<Item> items;
    public boolean has_more;
    public int backoff;
    public int quota_max;
    public int quota_remaining;

    public static class Item {
        public Owner owner;
        public boolean is_accepted;
        public int score;
        public long last_activity_date;
        public long last_edit_date;
        public long creation_date;
        public long answer_id;
        public long question_id;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return is_accepted == item.is_accepted &&
                    score == item.score &&
                    last_activity_date == item.last_activity_date &&
                    last_edit_date == item.last_edit_date &&
                    creation_date == item.creation_date &&
                    answer_id == item.answer_id &&
                    question_id == item.question_id &&
                    Objects.equals(owner, item.owner);
        }

        @Override
        public int hashCode() {
            return Objects.hash(owner, is_accepted, score, last_activity_date, last_edit_date, creation_date, answer_id, question_id);
        }

        public static class Owner {
            public int reputation;
            public long user_id;
            public String user_type;
            public int accept_rate;
            public String profile_image;
            public String display_name;
            public String link;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Owner owner = (Owner) o;
                return reputation == owner.reputation &&
                        user_id == owner.user_id &&
                        accept_rate == owner.accept_rate &&
                        Objects.equals(user_type, owner.user_type) &&
                        Objects.equals(profile_image, owner.profile_image) &&
                        Objects.equals(display_name, owner.display_name) &&
                        Objects.equals(link, owner.link);
            }

            @Override
            public int hashCode() {
                return Objects.hash(reputation, user_id, user_type, accept_rate, profile_image, display_name, link);
            }
        }
    }
}











