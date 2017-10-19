package gurshid.example2.Events;

/**
 * Created by Gurshid on 3/27/2017.
 */
public class Event {

        private String title;

        private String link;

        private String imageLink;

        public Event(String title, String link, String imageLink) {
            this.title = title;
            this.link = link;
            this.imageLink = imageLink;
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }

        public String getImageLink() {
            return imageLink;
        }
    }

