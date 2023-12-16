USE video_app;

insert into users(username, name, email, ts) values (
    'luisinha', 'Luísa', 'luisa07@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'ddoria', 'Daniel', 'd_doria@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'vbrunos', 'Vitor', 'brunosantos@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'guisoscas', 'Guilherme', 'gui_figueiredo@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'freixinho', 'Raquel', 'rachel_freixo@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'toni', 'António', 'antonio.p04@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'benny', 'Bernardo', 'bernardo_godinho@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'babynois', 'Catarina', 'catarina@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'figas', 'João', 'j.figueiredo@gmail.com', toTimestamp(now())
);
insert into users(username, name, email, ts) values (
    'andra', 'André', 'andre.vieira@gmail.com', toTimestamp(now())
);

insert into videos(id, author, video_name, description, tags, ts) values (
    1, 'luisinha', 'Entrevista ao Senhor Presidente', 'momentos inesqueciveis do jornalismo', {'documentary', 'politic'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    2, 'luisinha', 'A vida de um jornalista', 'jornalismo é o melhor da flup', {'education', 'casual', 'life'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    3, 'luisinha', 'Venda de bolachas dos escuteiros', 'o dinheiro das bolachas será convertido em viagens', {'vlog', 'scouts', 'casual'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    4, 'ddoria', 'O dia em que o toni me mandou com uma raquete na boca', 'tive de ir ao dentista no dia a seguir', {'vlog', 'life', 'comedy'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    5, 'ddoria', 'A tuna de FEUP', 'nunca achei que fosse consumir tanto álcool', {'university', 'comedy'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    6, 'vbrunos', 'Como é ser trabalhador-estudante?', 'estudar durante o dia e trabalhar à noite', {'q&a', 'university', 'life'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    7, 'vbrunos', 'A grandiosa tuna da FDUP', 'existe tempo para tudo, divirtam-se', {'university', 'life'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    8, 'guisoscas', 'Deitar cedo e cedo erguer', 'deitar as 4h e acordar às 5h', {'scouts', 'casual'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    9, 'freixinho', 'O mistério da física', 'o mundo e as suas leis', {'university', 'mistery'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    10, 'toni', 'Como mudar as rodas do skate?', 'cuidado com os rolamentos', {'sports', 'casual'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    11, 'toni', 'Fifa23 Gameplay', 'cartas novas e cartas para venda', {'sports', 'gaming'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    12, 'benny', 'World Padel Tour Show', 'o que acontece por detrás dos jogos', {'sports', 'lifestyle'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    13, 'babynois', 'Eterna mancha amarela', 'a vida de uma comissão de praxe', {'university', 'casual'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    14, 'figas', 'Uma noite no tijolas', 'servir finos, aturar bêbados e trajar todo o dia', {'university', 'life'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    15, 'andra', 'Interrail Portugal', 'a tuna levou-me a passear', {'university', 'vlog', 'comedy'}, toTimestamp(now()) 
);
insert into videos(id, author, video_name, description, tags, ts) values (
    16, 'ddoria', 'A ria', 'a veneza portuguesa', {'university', 'Aveiro'}, toTimestamp(now())
);

insert into comments(id, video_id, author, comment, ts) values (
    1, 3, 'guisoscas', 'one love escuteiros', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    2, 2, 'freixinho', 'you slay luisinha', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    3, 3, 'andra', 'estou à espera das bolachas', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    4, 4, 'toni', 'oh mano, foi sem querer', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    5, 4, 'figas', 'ahahah que sarda', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    6, 4, 'benny', 'só podia ser o antónio', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    7, 5, 'vbrunos', 'qual teup qual quê?? TAFDUP!!', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    8, 5, 'andra', 'mano, quando é que gravaste isto? eu estava a dormir', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    9, 5, 'andra', 'somos tão lindos', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    10, 5, 'figas', 'andra, estás sempre', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    11, 9, 'ddoria', 'VAI DORMIRRR!!', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    12, 11, 'figas', 'not joking, parecias o speed a abrir o pack', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    13, 12, 'babynois', 'vai bestie, és tão papi, ganha a essa malta toda!', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    14, 12, 'freixinho', 'bem benny, boa competicao', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    15, 12, 'ddoria', 'traz a taca para casa', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    16, 14, 'luisinha', 'nãoooo, tijolas nãooo! onde está o meu fino?', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    17, 14, 'ddoria', 'eia bem, quase que nem me lembro de ir ai. foi boa a noite', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    18, 15, 'babynois', 'não te perdeste, andré?', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    19, 15, 'toni', 'vou ser infiltrado no próximo', toTimestamp(now())
);
insert into comments(id, video_id, author, comment, ts) values (
    20, 15, 'ddoria', 'para a próxima traz tenda!', toTimestamp(now())
);

insert into followers(video_id, user_follow) values (
    1, 'freixinho'
);
insert into followers(video_id, user_follow) values (
    2, 'freixinho'
);
insert into followers(video_id, user_follow) values (
    2, 'vbrunos'
);
insert into followers(video_id, user_follow) values (
    2, 'babynois'
);
insert into followers(video_id, user_follow) values (
    3, 'guisoscas'
);
insert into followers(video_id, user_follow) values (
    3, 'andra'
);
insert into followers(video_id, user_follow) values (
    4, 'freixinho'
);
insert into followers(video_id, user_follow) values (
    4, 'benny'
);
insert into followers(video_id, user_follow) values (
    4, 'figas'
);
insert into followers(video_id, user_follow) values (
    4, 'andra'
);
insert into followers(video_id, user_follow) values (
    4, 'toni'
);
insert into followers(video_id, user_follow) values (
    5, 'freixinho'
);
insert into followers(video_id, user_follow) values (
    5, 'andra'
);
insert into followers(video_id, user_follow) values (
    5, 'babynois'
);
insert into followers(video_id, user_follow) values (
    5, 'figas'
);
insert into followers(video_id, user_follow) values (
    6, 'luisinha'
);
insert into followers(video_id, user_follow) values (
    6, 'babynois'
);
insert into followers(video_id, user_follow) values (
    7, 'luisinha'
);
insert into followers(video_id, user_follow) values (
    8, 'ddoria'
);
insert into followers(video_id, user_follow) values (
    8, 'benny'
);
insert into followers(video_id, user_follow) values (
    8, 'figas'
);
insert into followers(video_id, user_follow) values (
    9, 'benny'
);
insert into followers(video_id, user_follow) values (
    9, 'figas'
);
insert into followers(video_id, user_follow) values (
    10, 'ddoria'
);
insert into followers(video_id, user_follow) values (
    11, 'ddoria'
);
insert into followers(video_id, user_follow) values (
    11, 'guisoscas'
);
insert into followers(video_id, user_follow) values (
    11, 'figas'
);
insert into followers(video_id, user_follow) values (
    12, 'freixinho'
);
insert into followers(video_id, user_follow) values (
    12, 'ddoria'
);
insert into followers(video_id, user_follow) values (
    12, 'figas'
);
insert into followers(video_id, user_follow) values (
    12, 'toni'
);
insert into followers(video_id, user_follow) values (
    12, 'babynois'
);
insert into followers(video_id, user_follow) values (
    12, 'luisinha'
);
insert into followers(video_id, user_follow) values (
    12, 'guisoscas'
);
insert into followers(video_id, user_follow) values (
    13, 'vbrunos'
);
insert into followers(video_id, user_follow) values (
    13, 'figas'
);
insert into followers(video_id, user_follow) values (
    13, 'ddoria'
);
insert into followers(video_id, user_follow) values (
    14, 'freixinho'
);
insert into followers(video_id, user_follow) values (
    14, 'ddoria'
);
insert into followers(video_id, user_follow) values (
    14, 'andra'
);
insert into followers(video_id, user_follow) values (
    14, 'babynois'
);
insert into followers(video_id, user_follow) values (
    14, 'luisinha'
);
insert into followers(video_id, user_follow) values (
    15, 'ddoria'
);
insert into followers(video_id, user_follow) values (
    15, 'toni'
);
insert into followers(video_id, user_follow) values (
    15, 'vbrunos'
);
insert into followers(video_id, user_follow) values (
    15, 'figas'
);

insert into events(video_id, username, type, ts, moment) values (
    5, 'figas', 'play', toTimestamp(now()), 0
);
insert into events(video_id, username, type, ts, moment) values (
    5, 'figas', 'pause', toTimestamp(now()), 20
);
insert into events(video_id, username, type, ts, moment) values (
    5, 'figas', 'play', toTimestamp(now()), 25
);
insert into events(video_id, username, type, ts, moment) values (
    11, 'ddoria', 'play', toTimestamp(now()), 0
);
insert into events(video_id, username, type, ts, moment) values (
    11, 'ddoria', 'stop', toTimestamp(now()), 50
);
insert into events(video_id, username, type, ts, moment) values (
    14, 'guisoscas', 'play', toTimestamp(now()), 0
);
insert into events(video_id, username, type, ts, moment) values (
    8, 'benny', 'play', toTimestamp(now()), 0
);
insert into events(video_id, username, type, ts, moment) values (
    8, 'benny', 'stop', toTimestamp(now()), 48
);
insert into events(video_id, username, type, ts, moment) values (
    4, 'toni', 'play', toTimestamp(now()), 0
);
insert into events(video_id, username, type, ts, moment) values (
    4, 'toni', 'pause', toTimestamp(now()), 250
);

insert into ratings(id, video_id, rating) values (
    1, 2, 4
);
insert into ratings(id, video_id, rating) values (
    2, 2, 3
);
insert into ratings(id, video_id, rating) values (
    3, 4, 4
);
insert into ratings(id, video_id, rating) values (
    4, 4, 5
);
insert into ratings(id, video_id, rating) values (
    5, 4, 4
);
insert into ratings(id, video_id, rating) values (
    6, 8, 5
);
insert into ratings(id, video_id, rating) values (
    7, 8, 4
);
insert into ratings(id, video_id, rating) values (
    8, 12, 5
);
insert into ratings(id, video_id, rating) values (
    9, 12, 5
);
insert into ratings(id, video_id, rating) values (
    10, 12, 4
);
insert into ratings(id, video_id, rating) values (
    11, 14, 3
);
insert into ratings(id, video_id, rating) values (
    12, 14, 3
);
insert into ratings(id, video_id, rating) values (
    13, 14, 5
);
insert into ratings(id, video_id, rating) values (
    14, 14, 4
);
insert into ratings(id, video_id, rating) values (
    15, 14, 5
);
insert into ratings(id, video_id, rating) values (
    16, 15, 4
);
insert into ratings(id, video_id, rating) values (
    17, 15, 5
);

insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    1, 'luisinha', 'Entrevista ao Senhor Presidente', 'momentos inesqueciveis do jornalismo', {'documentary', 'politic'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    2, 'luisinha', 'A vida de um jornalista', 'jornalismo e o melhor da flup', {'education', 'casual', 'life'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    3, 'luisinha', 'Venda de bolachas dos escuteiros', 'o dinheiro das bolachas sera convertido em viagens', {'vlog', 'scouts', 'casual'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    4, 'ddoria', 'O dia em que o toni me mandou com uma raquete na boca', 'tive de ir ao dentista no dia a seguir', {'vlog', 'life', 'comedy'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    5, 'ddoria', 'A tuna de FEUP', 'nunca achei que fosse consumir tanto alcool', {'university', 'comedy'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    6, 'vbrunos', 'Como e ser trabalhador-estudante?', 'estudar durante o dia e trabalhar a noite', {'qea', 'university', 'life'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    7, 'vbrunos', 'A grandiosa tuna da FDUP', 'existe tempo para tudo, divirtam-se', {'university', 'life'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    8, 'guisoscas', 'Deitar cedo e cedo erguer', 'deitar as 4h e acordar as 5h', {'scouts', 'casual'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    9, 'freixinho', 'O misterio da fisica', 'o mundo e as suas leis', {'university', 'mistery'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    10, 'toni', 'Como mudar as rodas do skate?', 'cuidado com os rolamentos', {'sports', 'casual'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    11, 'toni', 'Fifa23 Gameplay', 'cartas novas e cartas para venda', {'sports', 'gaming'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    12, 'benny', 'World Padel Tour Show', 'o que acontece por detras dos jogos', {'sports', 'lifestyle'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    13, 'babynois', 'Eterna mancha amarela', 'a vida de uma comissao de praxe', {'university', 'casual'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    14, 'figas', 'Uma noite no tijolas', 'servir finos, aturar bebados e trajar todo o dia', {'university', 'life'}, toTimestamp(now()) 
);
insert into videos_by_author(id, author, video_name, description, tags, ts) values (
    15, 'andra', 'Interrail Portugal', 'a tuna levou-me a passear', {'university', 'vlog', 'comedy'}, toTimestamp(now()) 
);

insert into comments_by_user(id, video_id, author, comment, ts) values (
    1, 3, 'guisoscas', 'one love escuteiros', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    2, 2, 'freixinho', 'you slay luisinha', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    3, 3, 'andra', 'estou à espera das bolachas', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    4, 4, 'toni', 'oh mano, foi sem querer', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    5, 4, 'figas', 'ahahah que sarda', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    6, 4, 'benny', 'só podia ser o antónio', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    7, 5, 'vbrunos', 'qual teup qual quê?? TAFDUP!!', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    8, 5, 'andra', 'mano, quando é que gravaste isto? eu estava a dormir', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    9, 5, 'andra', 'somos tão lindos', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    10, 5, 'figas', 'andra, estás sempre', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    11, 9, 'ddoria', 'VAI DORMIRRR!!', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    12, 11, 'figas', 'not joking, parecias o speed a abrir o pack', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    13, 12, 'babynois', 'vai bestie, és tão papi, ganha a essa malta toda!', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    14, 12, 'freixinho', 'bem benny, boa competicao', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    15, 12, 'ddoria', 'traz a taca para casa', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    16, 14, 'luisinha', 'nãoooo, tijolas nãooo! onde está o meu fino?', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    17, 14, 'ddoria', 'eia bem, quase que nem me lembro de ir ai. foi boa a noite', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    18, 15, 'babynois', 'não te perdeste, andré?', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    19, 15, 'toni', 'vou ser infiltrado no próximo', toTimestamp(now())
);
insert into comments_by_user(id, video_id, author, comment, ts) values (
    20, 15, 'ddoria', 'para a próxima traz tenda!', toTimestamp(now())
);

insert into comments_by_video(id, video_id, author, comment, ts) values (
    1, 3, 'guisoscas', 'one love escuteiros', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    2, 2, 'freixinho', 'you slay luisinha', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    3, 3, 'andra', 'estou à espera das bolachas', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    4, 4, 'toni', 'oh mano, foi sem querer', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    5, 4, 'figas', 'ahahah que sarda', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    6, 4, 'benny', 'só podia ser o antónio', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    7, 5, 'vbrunos', 'qual teup qual quê?? TAFDUP!!', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    8, 5, 'andra', 'mano, quando é que gravaste isto? eu estava a dormir', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    9, 5, 'andra', 'somos tão lindos', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    10, 5, 'figas', 'andra, estás sempre', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    11, 9, 'ddoria', 'VAI DORMIRRR!!', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    12, 11, 'figas', 'not joking, parecias o speed a abrir o pack', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    13, 12, 'babynois', 'vai bestie, és tão papi, ganha a essa malta toda!', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    14, 12, 'freixinho', 'bem benny, boa competicao', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    15, 12, 'ddoria', 'traz a taca para casa', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    16, 14, 'luisinha', 'nãoooo, tijolas nãooo! onde está o meu fino?', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    17, 14, 'ddoria', 'eia bem, quase que nem me lembro de ir ai. foi boa a noite', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    18, 15, 'babynois', 'não te perdeste, andré?', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    19, 15, 'toni', 'vou ser infiltrado no próximo', toTimestamp(now())
);
insert into comments_by_video(id, video_id, author, comment, ts) values (
    20, 15, 'ddoria', 'para a próxima traz tenda!', toTimestamp(now())
);