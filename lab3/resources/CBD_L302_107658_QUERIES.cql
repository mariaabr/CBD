// alinea b)
select * from users;
select * from videos;
select * from videos_by_author;
select * from comments;
select * from comments_by_user;
select * from comments_by_video;
select * from followers;
select * from events;
select * from ratings;

// alinea c)
// 7. Permitir a pesquisa de todos os videos de determinado autor;
select * from videos_by_author;
// 8. Permitir a pesquisa de comentarios por utilizador, ordenado inversamente pela data;
select * from comments_by_user;
// 9. Permitir a pesquisa de comentarios por videos, ordenado inversamente pela data;
select * from comments_by_video;
// 10. Permitir a pesquisa do rating medio de um video e quantas vezes foi votado;
select * from ratings;


// 1. Os ultimos 3 comentarios introduzidos para um video;
select * from comments_by_video where video_id = 4 order by ts desc limit 3;

 video_id | ts                              | author | comment                 | id
----------+---------------------------------+--------+-------------------------+----
        4 | 2023-11-16 16:03:00.398000+0000 |  benny |  só podia ser o antónio |  6
        4 | 2023-11-16 16:03:00.397000+0000 |  figas |        ahahah que sarda |  5
        4 | 2023-11-16 16:03:00.395000+0000 |   toni | oh mano, foi sem querer |  4

(3 rows)

// 2. Lista das tags de determinado video;
select tags from videos where id = 15;

 tags
----------------------------------
 {'comedy', 'university', 'vlog'}

(1 rows)

// 3. Todos os videos com a tag Aveiro;
só funciona se aplicarmos o allow filtering porque o set tags não pertence à primary key
select * from videos where tags contains 'Aveiro' allow filtering;

 id | ts                              | author | description         | tags                     | video_name
----+---------------------------------+--------+---------------------+--------------------------+------------
 16 | 2023-11-17 11:08:55.490000+0000 | ddoria | a veneza portuguesa | {'Aveiro', 'university'} |      A ria

(1 rows)

// 4. Os ultimos 5 eventos de determinado video realizados por um utilizador;
select * from events where username = 'figas' and video_id = 5 order by ts desc limit 5;

 video_id | username | ts                              | moment | type
----------+----------+---------------------------------+--------+-------
        5 |    figas | 2023-11-16 16:03:00.295000+0000 |     25 |  play
        5 |    figas | 2023-11-16 16:03:00.293000+0000 |     20 | pause
        5 |    figas | 2023-11-16 16:03:00.291000+0000 |      0 |  play

(3 rows)

// 5. Videos partilhados por determinado utilizador (maria1987, por exemplo) num determinado periodo de tempo (Agosto de 2017, por exemplo);
select * from videos_by_author  where author = 'ddoria' and ts > '2023-11-17';

 author | ts                              | description         | id | tags                     | video_name
--------+---------------------------------+---------------------+----+--------------------------+------------
 ddoria | 2023-11-17 11:42:28.872000+0000 | a veneza portuguesa | 16 | {'Aveiro', 'university'} |      A ria

(1 rows)

// 6. Os ultimos 10 videos, ordenado inversamente pela data da partilhada;
query is not possible because clustering key restricts the query
select * from videos order by ts desc limit 10;
InvalidRequest: Error from server: code=2200 [Invalid query] message="ORDER BY is only supported when the partition key is restricted by an EQ or an IN."

// 7. Todos os seguidores (followers) de determinado video;
select * from followers where video_id = 9;

 video_id | users_follow
----------+--------------------
        9 | {'benny', 'figas'}

(1 rows)

// 8. Todos os comentarios (dos videos) que determinado utilizador esta a seguir (following);
cassandra cannot support joins, it's not possible to join the table comments_by_user with the followers table

// 9. Os 5 videos com maior rating;
cassandra does not allow global querying

// 10. Uma query que retorne todos os videos e que mostre claramente a forma pela qual estao ordenados;
we can understand that the videos are only ordered inside the partition
select * from videos;

 id | ts                              | author    | description                                        | tags                             | video_name
----+---------------------------------+-----------+----------------------------------------------------+----------------------------------+-------------------------------------------------------
  5 | 2023-11-16 16:03:00.216000+0000 |    ddoria |        nunca achei que fosse consumir tanto álcool |         {'comedy', 'university'} |                                        A tuna de FEUP
 10 | 2023-11-16 16:03:00.225000+0000 |      toni |                          cuidado com os rolamentos |             {'casual', 'sports'} |                         Como mudar as rodas do skate?
 16 | 2023-11-17 11:08:55.490000+0000 |    ddoria |                                a veneza portuguesa |         {'Aveiro', 'university'} |                                                 A ria
 13 | 2023-11-16 16:03:00.231000+0000 |  babynois |                    a vida de uma comissão de praxe |         {'casual', 'university'} |                                 Eterna mancha amarela
 11 | 2023-11-16 16:03:00.227000+0000 |      toni |                   cartas novas e cartas para venda |             {'gaming', 'sports'} |                                       Fifa23 Gameplay
  1 | 2023-11-16 16:03:00.208000+0000 |  luisinha |               momentos inesqueciveis do jornalismo |       {'documentary', 'politic'} |                       Entrevista ao Senhor Presidente
  8 | 2023-11-16 16:03:00.221000+0000 | guisoscas |                       deitar as 4h e acordar às 5h |             {'casual', 'scouts'} |                             Deitar cedo e cedo erguer
  2 | 2023-11-16 16:03:00.210000+0000 |  luisinha |                      jornalismo é o melhor da flup |  {'casual', 'education', 'life'} |                               A vida de um jornalista
  4 | 2023-11-16 16:03:00.214000+0000 |    ddoria |             tive de ir ao dentista no dia a seguir |       {'comedy', 'life', 'vlog'} | O dia em que o toni me mandou com uma raquete na boca
 15 | 2023-11-16 16:03:00.235000+0000 |     andra |                          a tuna levou-me a passear | {'comedy', 'university', 'vlog'} |                                    Interrail Portugal
  7 | 2023-11-16 16:03:00.220000+0000 |   vbrunos |                existe tempo para tudo, divirtam-se |           {'life', 'university'} |                              A grandiosa tuna da FDUP
  6 | 2023-11-16 16:03:00.218000+0000 |   vbrunos |          estudar durante o dia e trabalhar à noite |    {'life', 'q&a', 'university'} |                     Como é ser trabalhador-estudante?
  9 | 2023-11-16 16:03:00.223000+0000 | freixinho |                             o mundo e as suas leis |        {'mistery', 'university'} |                                  O mistério da física
 14 | 2023-11-16 16:03:00.233000+0000 |     figas |   servir finos, aturar bêbados e trajar todo o dia |           {'life', 'university'} |                                  Uma noite no tijolas
 12 | 2023-11-16 16:03:00.229000+0000 |     benny |                o que acontece por detrás dos jogos |          {'lifestyle', 'sports'} |                                 World Padel Tour Show
  3 | 2023-11-16 16:03:00.212000+0000 |  luisinha | o dinheiro das bolachas será convertido em viagens |     {'casual', 'scouts', 'vlog'} |                      Venda de bolachas dos escuteiros

(16 rows)

// 11. Lista com as Tags existentes e o numero de videos catalogados com cada uma delas;
not possible, it would be necessary to crate another table with the tags set as a primary key
and a set who could store the videos with that specific tag

// 12.
the querys with the like operator require an index on the column that will be used even if it is the primary key
select * from users where username like '%s%';
InvalidRequest: Error from server: code=2200 [Invalid query] message="LIKE restriction is only supported on properly indexed columns. username LIKE '%s%' is not valid."

// 13.
select * from comments_by_user where author = 'ddoria';

 author | ts                              | comment                                                    | id | video_id
--------+---------------------------------+------------------------------------------------------------+----+----------
 ddoria | 2023-11-16 16:03:00.389000+0000 |                                 para a próxima traz tenda! | 20 |       15
 ddoria | 2023-11-16 16:03:00.385000+0000 | eia bem, quase que nem me lembro de ir ai. foi boa a noite | 17 |       14
 ddoria | 2023-11-16 16:03:00.381000+0000 |                                      traz a taca para casa | 15 |       12
 ddoria | 2023-11-16 16:03:00.373000+0000 |                                             VAI DORMIRRR!! | 11 |        9

(4 rows)