CREATE KEYSPACE IF NOT EXISTS video_app WITH replication = {'class': 'SimpleStrategy','replication_factor': 1};
USE video_app;

DROP TABLE IF EXISTS users;
CREATE TABLE users(
    username text primary key,
    name text,
    email text,
    ts timestamp
);

DROP TABLE IF EXISTS videos;
CREATE TABLE videos(
    id int,
    author text,
    video_name text,
    description text,
    tags set<text>,
    ts timestamp,
    primary key ((id), ts)
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments(
    id int primary key,
    video_id int,
    author text,
    comment text,
    ts timestamp
);

DROP TABLE IF EXISTS followers;
CREATE TABLE followers(
    video_id int,
    user_follow text,
    primary key (video_id, user_follow)
);

DROP TABLE IF EXISTS events;
CREATE TABLE events(
    video_id int,
    username text,
    type text,
    ts timestamp,
    moment int,
    primary key ((video_id, username), ts)
);

DROP TABLE IF EXISTS ratings;
CREATE TABLE ratings(
    id int,
    video_id int,
    rating int,
    primary key ((id, video_id), rating)
);

DROP TABLE IF EXISTS videos_by_author;
CREATE TABLE videos_by_author(
    id int,
    author text,
    video_name text,
    description text,
    tags set<text>,
    ts timestamp,
    primary key ((author), ts)
);

DROP TABLE IF EXISTS comments_by_user;
CREATE TABLE comments_by_user(
    id int,
    video_id int,
    author text,
    comment text,
    ts timestamp,
    primary key (author, ts))
WITH CLUSTERING ORDER BY (ts DESC);

DROP TABLE IF EXISTS comments_by_video;
CREATE TABLE comments_by_video(
    id int,
    video_id int,
    author text,
    comment text,
    ts timestamp,
    primary key (video_id, ts, author))
WITH CLUSTERING ORDER BY (ts DESC, author ASC);

DROP TABLE IF EXISTS rating_medio;
CREATE TABLE rating_medio(
    rating int,
    video_id int,
    avg_rating double,
    num_rates int,
    primary key ((video_id, avg_rating), num_rates)
);