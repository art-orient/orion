USE orion;

INSERT INTO users
VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
        'Александр', 'Артихович', 'art-orient@tut.by', 0, 1);  -- password=admin
INSERT INTO users
VALUES ('natarti', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
        'Наталья', 'Артихович', 'natarti75@gmail.com', 2, 1);  -- password=123456
INSERT INTO users
VALUES ('lesha', 'd17f25ecfbcc7857f7bebea469308be0b2580943e96d13a3ad98a13675c4bfc2',
        'Lesha', 'Artsikhovich', 'art22@tut.by', 2, 1);  -- password=11111
INSERT INTO users
VALUES ('andrei', 'b9352494463399aa6a44ed5e39425b8a0bc39b2fc3d0184ba2583ce9bc4e4c1d',
        'Андрей', 'Андреев', 'andrei@and.com', 2, 1);  -- password=andrei
INSERT INTO users
VALUES ('alexei', '3c4c0aec843b3d5164285a367f92794eee300bafdd1ee56407bd680f0898bb44',
        'Алексей', 'Алексеев', 'alekseev@al.com', 2, 1);  -- password=alexei
INSERT INTO users
VALUES ('pavel', 'b4b6e5deeec1253972cd0ec230e2951c5b2518c19cf9aa4198ee8731fee58795',
        'Pavel', 'Pavlonus', 'pablo@eu.lt', 2, 1);  -- password=pavel

INSERT INTO accessories
VALUES (1, 'Рюкзак', 'A backpack', 'Mizuno', 'Running Backpack 10L', 'РЮКЗАК MIZUNO УНИСЕКС
100% полиэстер
Легкий материал
Мягкие регулируемые лямки
Вместительное центральное отделение на молнии
Карман спереди на молнии
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Ширина(W) 20см х Высота(H) 42см', 'MIZUNO UNISEX BACKPACK
100% Polyester
Lightweight material
Padded adjustable shoulder straps
Spacious central zip compartment
Front zip pocket
Reflective elements to increase the level of safety of movement in the dark
Width (W) 20cm x Height (H) 42cm', 'Mizuno_Running_Backpack_10L.jpg', 99.00, 7, 1);

INSERT INTO accessories
VALUES (2, 'Сумка спортивная', 'Sport bag', 'Mizuno', 'Running Waist Bottle Bag', 'СПОРТИВНАЯ СУМКА-ПОЯС MIZUNO УНИСЕКС
100% полиэстер
Карман на молнии
Карман для бутылки
Регулируемый ремень
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Длина(L) 32см х Высота(H) 18см', 'MIZUNO UNISEX SPORTS BELT
100% Polyester
Zip pocket
Bottle pocket
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark
Length (L) 32cm x Height (H) 18cm', 'Mizuno_Running_Waist_Bottle_Bag.jpg', 84.00, 4, 1);

INSERT INTO accessories
VALUES (3, 'Поясная сумка для бега', 'Running belt bag', 'Asics', 'WAIST POUCH', 'Сумка на пояс, позволяющая взять с собой на пробежку телефон, гели, ключи и другие важные мелочи. Основное отделение закрывается на молнию. Впереди сквозной карман из эластичной растягивающейся ткани, идеально подходит для хранения трикотажных беговых аксессуаров, например: повязки, баффа, рукавов.

Основа сумки из прочной объемной ткани-сетки рипстоп с отличным воздухообменом. Ремень с замком на карабине можно регулировать по объему талии. Светоотражающие элементы для безопасных тренировок в темное время суток.', 'A belt bag that allows you to take your phone, gels, keys and other important things with you for a run. The main compartment closes with a zipper. Front side pocket made of elastic stretch fabric, ideal for storing jersey running accessories such as bandage, buff, sleeves.

The base of the bag is made of durable ripstop mesh fabric with excellent breathability. The carabiner strap can be adjusted to fit the waist. Reflective elements for safe workouts in the dark. ', 'Asics_WAIST_POUCH.jpg', 39.00, 3, 1);

INSERT INTO accessories
VALUES (4, 'Бейсболка', 'Baseball cap', 'Asics', 'Lightweight Running Cap', 'БЕЙСБОЛКА ASICS УНИСЕКС
56% полиэстер / 32% нейлон / 12% спандекс
Легкий, воздухопроницаемый материал с влаговыводящими свойствами
Ремешок для регулировки размера
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'ASICS UNISEX BASEBALL CAP
56% polyester / 32% nylon / 12% spandex
Lightweight, breathable material with moisture wicking properties
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark', 'Asics_Lightweight_Running_Cap.jpg', 54.00, 10, 1);

INSERT INTO accessories
VALUES (5, 'Ультралегкий козырек', 'Ultra light visor', 'Compressport', 'VISOR white', 'Ультралегкий козырек Compressport Spiderweb идеально подходит для бегунов и триатлетов. Козырек защитит ваши глаза от яркого солнца, ветра, а его мягкая, воздухопроницаемая ткань впитает влагу/пот и быстро высохнет.

• материал: 60% полиэстер. 30% хлопок, 10% эластан;
• подкладка в области лба из сетчатого материала с антибактериальной обработкой;
• удобная посадка благодаря эластичной, бесшовной конструкции;
• жесткий козырек.', 'The ultra-light Compressport Spiderweb visor is ideal for runners and triathletes. The visor will protect your eyes from the bright sun, wind, and its soft, breathable fabric wicks moisture / sweat and dries quickly.

• material: 60% polyester. 30% cotton, 10% elastane;
• lining in the forehead area made of mesh material with antibacterial treatment;
• comfortable fit thanks to the elastic, seamless construction;
• rigid visor.', 'Compressport_VISOR_white.jpg', 69.00, 2, 1);

INSERT INTO accessories
VALUES (6, 'Рюкзак', 'A backpack', 'Asics', 'Lightweight Running Backpack', 'РЮКЗАК ASICS УНИСЕКС
100% полиэстер
Легкий, прочный материал с водоотталкивающими свойствами
Объем 10 литров
Регулируемые лямки из сетки
Вместительное центральное отделение
Карман спереди на молнии
Боковые карманы из сетки
Карман на лямке рюкзака
Карман на молнии на поясной застежке
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Ширина(W) 23см х Высота(H) 44см', 'ASICS UNISEX BACKPACK
100% Polyester
Lightweight, durable material with water repellent properties
Volume 10 liters
Adjustable mesh straps
Spacious central compartment
Front zip pocket
Mesh side pockets
Backpack strap pocket
Zip pocket with belt closure
Reflective elements to increase the level of safety of movement in the dark
Width (W) 23cm x Height (H) 44cm', 'Asics_Lightweight_Running_Backpack.jpg', 124.00, 5, 1);

INSERT INTO accessories
VALUES (7, 'Козырек', 'Visor', 'Salomon', 'SENSE VISOR PAPAYA',
 'Козырек Salomon SENSE VISOR PAPAYA защитит ваши глаза от яркого солнца и ветра.',
  'The Salomon SENSE VISOR PAPAYA protects your eyes from bright sun and wind.',
   'Salomon_SENSE_VISOR_PAPAYA.jpg', 94.00, 5, 1);

INSERT INTO accessories
VALUES (8, 'Рюкзак', 'A backpack', 'Mizuno', 'Lightweight Running Backpack', 'РЮКЗАК MIZUNO УНИСЕКС
100% полиэстер
Легкий материал
Мягкие регулируемые лямки
Вместительное центральное отделение на молнии
Карман спереди на молнии
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Ширина(W) 20см х Высота(H) 42см', 'MIZUNO UNISEX BACKPACK
100% Polyester
Lightweight material
Padded adjustable shoulder straps
Spacious central zip compartment
Front zip pocket
Reflective elements to increase the level of safety of movement in the dark
Width (W) 20cm x Height (H) 42cm', 'Mizuno_Running_Backpack_10L_black.jpg', 99.00, 3, 1);

INSERT INTO accessories
VALUES (9, 'Перчатки-варежки', 'Gloves-mittens', 'Reusch', 'Terro Stormbloxx',
 'Эта эластичная и легкая флисовая перчатка, включающая убираемый полностью ветрозащитный капюшон на тыльной стороне ладони, снабжена силиконовым принтом на ладони, светоотражающими элементами и специальной аппликацией на кончике большого и указательного пальцев, которая позволяет использовать мобильные устройства.',
  'Featuring a fully retractable windproof hood on the back of the hand, this elastic and lightweight fleece glove features a silicone print on the palm, reflective detailing and a dedicated thumb and index fingertip appliqué for mobile use.',
   'Reusch_Terro_Stormbloxx.jpg', 94.00, 10, 1);

INSERT INTO accessories
VALUES (10, 'Перчатки', 'Gloves', 'Mizuno', 'Warmalite Glove', 'БЕГОВЫЕ ПЕРЧАТКИ MIZUNO УНИСЕКС
88% полиэстер / 12% эластан
Легкий, ветрозащитный материал с влаговыводящими свойствами
Технология WarmaLite сохраняет тепло при занятиях в условиях низких температур
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'MIZUNO UNISEX RUNNING GLOVES
88% polyester / 12% elastane
Lightweight, windproof material with moisture wicking properties
WarmaLite technology keeps you warm when exercising in low temperatures
Reflective elements to increase the level of safety of movement in the dark', 'Mizuno_Warmalite_Glove.jpg', 79.00, 7, 1);

INSERT INTO accessories
VALUES (11, 'Перчатки', 'Gloves', 'Asics', 'Lite Show Gloves', 'БЕГОВЫЕ ПЕРЧАТКИ ASICS
78% полиэстер / 22% вискоза
Легкий, ветрозащитный материал с влаговыводящими свойствами
Технология Motion Dry позволяет коже свободно дышать, отводит влагу и сохраняет тело в сухости и при этом препятствует образованию неприятного запаха
Специальный материал на подушечках пальцев для сенсорного экрана
Защита от UF-лучей
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'ASICS RUNNING GLOVES
78% polyester / 22% viscose
Lightweight, windproof material with moisture wicking properties
Motion Dry technology allows skin to breathe freely, wicks moisture away and keeps the body dry while preventing unpleasant odors
Dedicated material on the fingertips for the touch screen
UV protection
Reflective elements to increase the level of safety of movement in the dark', 'Asics_Lite_Show_Gloves.jpg', 64.00, 20, 1);

INSERT INTO accessories
VALUES (12, 'Перчатки', 'Gloves', 'Dare2b', 'Cogent Glove', 'БЕГОВЫЕ УТЕПЛЕННЫЕ ПЕРЧАТКИ УНИСЕКС
100% полиэстер
Мягкий материал с влаговыводящими свойствами
Специальный материал на подушечках указательного и большого пальцев для сенсорного экрана
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'UNISEX INSULATED RUNNING GLOVES
100% Polyester
Soft material with moisture wicking properties
Dedicated material on the thumb and forefinger pads for touchscreen
Reflective elements to increase the level of safety of movement in the dark', 'Dare2b_Cogent_Glove.jpg', 64.00, 15, 1);

INSERT INTO accessories
VALUES (13, 'Перчатки', 'Gloves', 'Asics', 'Thermal Gloves', 'БЕГОВЫЕ УТЕПЛЕННЫЕ ПЕРЧАТКИ ASICS УНИСЕКС
100% полиэстер
Мягкий материал с влаговыводящими свойствами
Специальный материал на подушечках указательного и большого пальцев для сенсорного экрана
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'ASICS UNISEX INSULATED RUNNING GLOVES
100% Polyester
Soft material with moisture wicking properties
Dedicated material on the thumb and forefinger pads for touchscreen
Reflective elements to increase the level of safety of movement in the dark', 'Asics_Thermal_Gloves.jpg', 59.00, 20, 1);

INSERT INTO accessories
VALUES (14, 'Перчатки', 'Gloves', 'Swix', 'Tracx', 'Перчатки унисекс самые доступные перчатки в линейке SWIX для беговых лыж.
Качественные замшевые вставки, нетканая высококачественная микрофибра.', 'Unisex gloves are the most affordable gloves in the SWIX range for cross-country skiing.
Quality suede inserts, non-woven high quality microfiber.', 'Swix_Tracx.jpg', 49.00, 12, 1);

INSERT INTO accessories
VALUES (15, 'Сумка на пояс', 'Belt bag', 'Asics', 'Waist Pouch black', 'Сумка на пояс, позволяющая взять с собой на пробежку телефон, гели, ключи и другие важные мелочи. Основное отделение закрывается на молнию. Впереди сквозной карман из эластичной растягивающейся ткани, идеально подходит для хранения трикотажных беговых аксессуаров, например: повязки, баффа, рукавов.

Основа сумки из прочной объемной ткани-сетки рипстоп с отличным воздухообменом. Ремень с замком на карабине можно регулировать по объему талии. Светоотражающие элементы для безопасных тренировок в темное время суток.',
 'A belt bag that allows you to take your phone, gels, keys and other important things with you for a run. The main compartment closes with a zipper. Front side pocket made of elastic stretch fabric, ideal for storing jersey running accessories such as bandage, buff, sleeves.

The base of the bag is made of durable ripstop mesh fabric with excellent breathability. The strap with a carabiner can be adjusted to fit the waist. Reflective elements for safe workouts in the dark.', 'Asics_Waist_Pouch_black.jpg', 74.00, 8, 1);

INSERT INTO accessories
VALUES (16, 'Сумка спортивная', 'Sport bag', 'Mizuno', 'Running Waist Bag blue', 'СПОРТИВНАЯ СУМКА-ПОЯС MIZUNO УНИСЕКС
100% полиэстер
Карман на молнии
Регулируемый ремень
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Длина(L) 24см х Высота(H) 10см',
 'MIZUNO UNISEX SPORTS BELT
100% Polyester
Zip pocket
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark
Length (L) 24cm x Height (H) 10cm', 'Mizuno_Running_Waist_Bag_blue.jpg', 64.00, 15, 1);

INSERT INTO accessories
VALUES (17, 'Сумка спортивная', 'Sport bag', 'Mizuno', 'Running Waist Bag black', 'СПОРТИВНАЯ СУМКА-ПОЯС MIZUNO УНИСЕКС
100% полиэстер
Карман на молнии
Регулируемый ремень
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Длина(L) 24см х Высота(H) 10см',
 'MIZUNO UNISEX SPORTS BELT
100% Polyester
Zip pocket
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark
Length (L) 24cm x Height (H) 10cm', 'Mizuno_Running_Waist_Bag_black.jpg', 64.00, 15, 1);

INSERT INTO accessories
VALUES (18, 'Сумка спортивная', 'Sport bag', 'Asics', 'Waistpack Rose', 'CПОРТИВНАЯ СУМКА-ПОЯС ASICS УНИСЕКС
84% нейлон / 16% спандекс
Карман на молнии для хранения вещей
Регулируемый ремень
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток',
 'ASICS UNISEX BELT BAG
84% nylon / 16% spandex
Zippered storage pocket
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark', 'Asics_Waistpack_Rose.jpg', 39.00, 8, 1);

INSERT INTO accessories
VALUES (19, 'Кепка', 'Cap', 'Regatta', 'Nautical Blu', 'Кепка Regatta для бега
100% полиэстер, регулировка - нейлоновая лента с PVC зажимом',
 'Regatta Running Cap
100% polyester, adjustable - nylon tape with PVC clip', 'Regatta_Nautical_Blu.jpg', 29.00, 8, 1);

INSERT INTO accessories
VALUES (20, 'Кепка', 'Cap', 'Atlantis', 'Runner', '', '', 'Atlantis_Runner.jpg', 24.00, 10, 1);

INSERT INTO accessories
VALUES (21, 'Очки спортивные', 'Sports glasses', 'XLC', 'Sulawesi SG-C10', 'Солнцезащитные очки Sulawesi SG-C10
Мягкая, гибкая оправа, с двумя сменными линзами, в прозрачных и очень легких оранжевых прорезиненных носовых накладках, в том числе из микрофибры, 100% защита от ультрафиолетовых лучей.',
 'Sulawesi SG-C10 sunglasses
Soft, flexible frame, with two interchangeable lenses, in transparent and very light orange rubberized nose pads, including microfiber, 100% UV protection.',
 'XLC_Sulawesi_SG-C10.jpg', 64.00, 6, 1);

INSERT INTO accessories
VALUES (22, 'Очки', 'Glasses', 'KV+', 'Vertical glasses', 'Спортивные очки KV+  отлично подходят для спорта. Они защищают от ультрафиолетового излучения на 100%, имеют небьющиеся поликарбонатные линзы и регулируемую переносицу.

Радужные (неполяризованные) линзы CW36 изготовлены из ударопрочного поликарбоната, который до 10 раз прочнее обычного пластика или стекла.
Линзы KV + имеют рейтинг UV400 и эффективно защищают ваши глаза от вредных лучей UVA и UVB.
Очки KV + Vertical оснащены регулируемыми носоупорами.
Материал оправы: Grilamid TR90
Все очки KV + сертифицированы CE
К очкам KV + Vertical прилагается мягкий чехол из микроволокна (без жесткого футляра).',
 'The KV + sports glasses are great for sports. They offer 100% UV protection, shatterproof polycarbonate lenses and an adjustable nose bridge.

The CW36 rainbow (non-polarized) lenses are made of impact-resistant polycarbonate, which is up to 10 times stronger than conventional plastic or glass.
KV + lenses are UV400 rated and effectively protect your eyes from harmful UVA and UVB rays.
KV + Vertical goggles are equipped with adjustable nose pads.
Frame Material: Grilamid TR90
All KV + glasses are CE certified
The KV + Vertical goggles come with a soft microfiber case (no hard case).',
 'KV+_Vertical_glasses.jpg', 79.00, 6, 1);


INSERT INTO clothing
VALUES (1, 'Брюки', 'Pants', 'Noname', 'Running pants unisex', 'Брюки Noname Running подойдут для беговых тренировок. Брюки анатомического кроя, идеально сидят по фигуре и обеспечивают полную свободу движений за счет лайкры на задней части брюк.

Описание товара:

Беговые брюки
Анатомический крой
1 карман
Лайкра полностью на задней части брюк
Светоотражающие элементы
Молния до колена',
 'The Noname Running trousers are perfect for running workouts. Anatomically cut trousers, fit perfectly on the figure and provide complete freedom of movement due to the lycra on the back of the trousers.

Product description:

Jogging Trousers
Anatomical cut
1 pocket
Lycra completely on the back of the pants
Reflective elements
Knee zipper',
 'Noname_Running_pants_unisex_blue.jpg', 'blue', 179.00, 1);

INSERT INTO clothing
VALUES (2, 'Тайтсы женские', 'Women`s tights', 'Mizuno', 'Premium Jpn Short Tight black', 'ЖЕНСКИЕ БЕГОВЫЕ ТАЙТСЫ MIZUNO

88% полиэстер / 12% спандекс

Легкий материал с влаговыводящими свойствами

Легкая компрессия для дополнительной поддержки основных групп мышц

Эластичный пояс

Задний карман на молнии

Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
',
 'WOMEN`S RUNNING TIGHTS MIZUNO

88% polyester / 12% elastane

Lightweight material with moisture wicking properties

Light compression for added support for major muscle groups

Elastic waist

Back pocket with zip

Reflective elements for increased driving safety at night
',
 'Mizuno_Premium_Jpn_Short_Tight_black.jpg', 'black', 79.00, 1);

INSERT INTO clothing
VALUES (3, 'Куртка мужская', 'Men`s jackets', 'Mizuno', 'Lightweight Jacket Blue', 'Спортивная куртка Mizuno.

100% полиэстер
Технология управления влажностью DryLite сохранит тело сухим
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток',
 'Sports jacket Mizuno.

100% Polyester
DryLite moisture management technology keeps body dry
Reflective elements for increased driving safety at night',
 'Mizuno_Lightweight_Jacket_Blue.jpg', 'blue', 134.00, 1);

INSERT INTO clothing
VALUES (4, 'Куртка женская', 'Female jacket', 'Noname', 'Running Jacket Pro Talwind', 'Спортивная куртка элитной серии Noname Pro Tail Wind для професиональных спортсменов и любителей спорта. Куртка анатомического кроя не сковывает движения за счет вставок из лайкры на боковых частях.

Куртка

Анатомический крой
Вставки из лайкры на боковых частях
Манжеты-перчатки
Светоотражающие элементы
1 карман на груди
Способ нанесения рисунка - лазерная печать',
 'Sports jacket of the elite Noname Pro Tail Wind series for professional athletes and sports fans. The anatomically cut jacket does not hinder movement due to Lycra inserts on the side parts.

Jacket

Anatomical cut
Lycra side panels
Gloves cuffs
Reflective elements
1 chest pocket
Method of drawing a picture - laser printing',
 'Noname_Running_Jacket_Pro_Talwind.jpeg', 'green', 124.00, 1);

INSERT INTO clothing
VALUES (5, 'Олимпийка', 'Olympian', 'Asics', 'Tokyo Warm Up Jacket', 'МУЖСКАЯ ОЛИМПИЙКА ASICS

100% полиэстер

Легкий материал с влаговыводящими свойствами

Боковые карманы',
 'MEN`S OLYMPIC ASICS

100% Polyester

Lightweight material with moisture wicking properties

Side pockets',
 'Asics_Tokyo_Warm_Up_Jacket.jpg', 'white', 144.00, 1);

INSERT INTO clothing
VALUES (6, 'Шорты мужские', 'Men`s Shorts', 'Asics', 'Silver Split Short grey', 'МУЖСКИЕ БЕГОВЫЕ ШОРТЫ ASICS

100% полиэстер

Легкий материал с влаговыводящими свойствами

Эластичный пояс со шнурком для регулировки размера',
 'MEN`S ASICS RUNNING SHORTS

100% Polyester

Lightweight material with moisture wicking properties

Elastic waist with drawcord for size adjustment',
 'Asics_Silver_Split_Short_grey.jpg', 'grey', 74.00, 1);

INSERT INTO clothing
VALUES (7, 'Шорты мужские', 'Men`s Shorts', 'Mizuno', 'Premium Jpn Split Short blue', 'МУЖСКИЕ БЕГОВЫЕ ШОРТЫ MIZUNO

100% полиэстер

Технология управления влажностью DryLite сохранит тело сухим

Эластичный пояс со шнурком для регулировки размера

Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
',
 'MEN`S MIZUNO RUNNING SHORTS

100% Polyester

DryLite moisture management technology keeps body dry

Elastic waist with drawcord for size adjustment

Reflective elements for increased driving safety at night
',
 'Mizuno_Premium_Jpn_Split_Short_blue.jpg', 'blue', 89.00, 1);

INSERT INTO clothing
VALUES (8, 'Шорты мужские', 'Men`s Shorts', 'Mizuno', 'Premium Jpn Split Short black', 'МУЖСКИЕ БЕГОВЫЕ ШОРТЫ MIZUNO

100% полиэстер

Технология управления влажностью DryLite сохранит тело сухим

Эластичный пояс со шнурком для регулировки размера

Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
',
 'MEN`S MIZUNO RUNNING SHORTS

100% Polyester

DryLite moisture management technology keeps body dry

Elastic waist with drawcord for size adjustment

Reflective elements for increased driving safety at night
',
 'Mizuno_Premium_Jpn_Split_Short_black.jpg', 'black', 89.00, 1);

INSERT INTO clothing
VALUES (9, 'Костюм мужской', 'Men`s suit', 'Noname', 'Robigo Running Suit Blue', 'Спортивный костюм элитной серии Noname Robigo для професиональных спортсменов и любителей спорта. Костюм анатомического кроя не сковывает движения за счет вставок из лайкры на боковых частях куртки, задняя часть брюк так же полностью сделана из лайкры.



Куртка



Анатомический крой

Вставки из лайкры на боковых частях

Манжеты-перчатки

Светоотражающие элементы

1 карман на груди

Брюки



Анатомический крой

1 карман

Задняя часть брюк из лайкры

Молния до колена

Силиконовая внутренняя окантовка на манжете

Манжеты на резинке

Светоотражающие элементы',
 'Sports suit of the elite Noname Robigo series for professional athletes and sports fans. The anatomically cut suit does not hinder movement due to Lycra inserts on the sides of the jacket, the back of the trousers is also completely made of Lycra.



Jacket



Anatomical cut

Lycra side panels

Gloves cuffs

Reflective elements

1 chest pocket

Pants



Anatomical cut

1 pocket

Lycra back of trousers

Knee zipper

Silicone inner cuff

Elasticated cuffs

Reflective elements',
 'Noname_Robigo_Running_Suit_Blue.jpeg', 'blue', 314.00, 1);

INSERT INTO clothing
VALUES (10, 'Тайтсы женские', 'Women`s tights', 'DARE2B', 'Curvate Tight', 'ЖЕНСКИЕ БЕГОВЫЕ ТАЙТСЫ

84% полиэстер / 16% эластан

Легкий материал с влаговыводящими свойствами

Эластичный пояс со шнурком для регулировки размера

Задний карман

Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток',
 'WOMEN`S RUNNING TIGHTS

84% polyester / 16% elastane

Lightweight material with moisture wicking properties

Elastic waist with drawcord for size adjustment

Back pocket

Reflective elements for increased driving safety at night',
 'DARE2B_Curvate_Tight.jpg', 'blue', 69.00, 1);

INSERT INTO clothing
VALUES (11, 'Шорты женские', 'Women`s shorts', 'Asics', 'Silver Split Short', 'Женские беговые шорты Asics Silver Split Short



100% полиэстер

Легкий материал с влаговыводящими свойствами

Эластичный пояс со шнурком для регулировки размера',
 'Women`s Running Shorts Asics Silver Split Short



100% Polyester

Lightweight material with moisture wicking properties

Elastic waist with drawcord for size adjustment
',
 'Asics_Silver_Split_Short.jpg', 'black', 64.00, 1);

INSERT INTO shoes
VALUES (1, 'Кроссовки для бега женские', 'Running shoes for women', 'Asics', 'Gel Excite 8 Digital Aqua', 'Беговые кроссовки Asics Gel Excite 8 обладают легкостью, улучшенной посадкой в передней части стопы и обеспечивают длительный комфорт при беге на любые дистанции. Верх выполнен из сетки, что обеспечивает превосходную вентиляцию во время бега. Подошва эффективно поглощает удары за счет вставок из геля, что значительно снижает напряжение во время беговых тренировок. Данная обувь обладает превосходным комфортом и пружинистостью, и является универсальной моделью для бегунов, ищущих простоту в обуви.

Верх выполнен из сетки для воздухопроницаемости
Стелька Ortholite обеспечивает сухость, легкость и комфорт
Промежуточная подошва с технологией AmpliFoam обеспечивает легкость и хороший уровень поглощения ударов при беге
Asics Гель в пятке снижает нагрузку на позвоночник, пятки и колени спортсмена
Гибкая резиновая подошва обеспечивает надежное сцепление с поверхностью',
 'The Asics Gel Excite 8 Running Shoe is lightweight, with an improved forefoot fit and long-lasting comfort on any distance run. The upper is made of mesh for superior ventilation while you run. The outsole effectively absorbs impact with gel inserts, which significantly reduces stress during running workouts. This shoe offers superior comfort and springiness, and is a versatile shoe for runners looking for simplicity in a shoe.

Mesh upper for breathability
Ortholite footbed keeps you dry, lightweight and comfortable
AmpliFoam midsole for lightweight and good shock absorption while running
Asics Heel Gel reduces stress on the athlete`s spine, heels and knees
Flexible rubber outsole provides reliable traction',
 'Asics_Gel_Excite_8_Digital_Aqua.jpg', 'aqwa/white', 244.00, 1);

INSERT INTO shoes
VALUES (2, 'Кроссовки для бега мужские', 'Running shoes for men', 'Asics', 'Gel-Contend 6 grey', 'Кроссовки для бега Asics Gel Contend 6

Беговые кроссовки Asics Gel Contend 6 обеспечивают длительную поддержку и превосходный комфорт. Более мягкий задник для создания ощущения шелковистости при соприкосновении к коже. Верх обуви выполнен из легкой сетки с синтетическими накладками для дополнительной прочности. Съемная стелька Ortholite ComforDry X-40обеспечивает отличную амортизацию, эффективно отводит излишки тепла и влаги, а также обладает бактерицидными свойствами, обеспечивая здоровый микроклимат внутри обуви.

Верх выполнен из легкой воздухопроницаемой сетки

Симметричная система шнуровки обеспечивает комфортную посадку

Внутренний задник для дополнительной поддержки

Технология AmpliFoam обеспечивает комфорт на каждом этапе пути

Система амортизации Rearfoot GEL Cushioning System расположена в задней части подошвы для амортизации

Износостойкая резиновая подошва отличается великолепной гибкостью и обеспечивает отличное сцепление на различных типах поверхности

Пронация: нейтральная.

Перепад: 10мм.',
 'The Asics Gel Contend 6 running shoe provides long-lasting support and superior comfort. A softer heel counter for a silky feel when in contact with skin. The shoe upper is made of lightweight mesh with synthetic overlays for added durability. The removable Ortholite ComforDry X-40 insole provides excellent cushioning, effectively removes excess heat and moisture, and also has antibacterial properties, providing a healthy microclimate inside the shoe.

- Upper made from lightweight breathable mesh

Symmetrical lacing system provides a comfortable fit

Internal heel counter for added support

AmpliFoam technology provides comfort every step of the way

Rearfoot GEL Cushioning System is located in the rear of the outsole for cushioning

Durable rubber outsole offers great flexibility and traction on a variety of terrains

Pronation: neutral.

Drop: 10mm.',
 'Asics_Gel-Contend_6_grey.jpg', 'grey', 119.00, 1);

INSERT INTO shoes
VALUES (3, 'Кроссовки для бега мужские', 'Running shoes for men', 'Asics', 'GlideRide 2', 'Кроссовки беговые ASICS GLIDERIDE 2

Кроссовки GLIDERIDE™ 2 позволяют экономить энергию во время долгих забегов. В этих кроссовках снижается расход энергии во всех фазах цикла бега от приземления до отталкивания, что поможет вам преодолеть большие расстояния, чем когда-либо раньше. В этом сезоне сохранилась экономия энергии в этих кроссовках и движения в них стали более плавными, а также улучшена посадка верха. Они предназначены для тех бегунов, которые хотят чувствовать себя бодрее после долгих забегов, и идеально подойдут для восстановительного бега.

В этой модели применяется функциональная сетка, улучшающая посадку верха и обеспечивающая превосходную вентиляцию, при этом адаптирующаяся к естественной форме стопы при движении. Бесшовные элементы, выполненные методом 3D печати, создают поддержку там, где она нужна, при этом не добавляя кроссовкам веса.

Технология GUIDESOLE™ – это жесткая передняя часть обуви и изогнутая подошва, повышающая эффективность бега за счет снижения нагрузки на ноги. Амортизирующая технология FLYTEFOAM™ располагается в верхнем слое промежуточной подошвы, а нижний слой по технологии FLYTEFOAM™ Propel обеспечивает более адаптивный отскок.

Между этими двумя слоями в передней части находится жесткая пластина, которая обеспечивает целостность промежуточной подошвы. Движение переката стопы вперед подталкивает ногу и обеспечивает более плавные и эффективные переходы между шагами. Кроме того, резиновая вставка AHARPLUS™ в подошве в области пятки повышает износостойкость и продлевает срок службы кроссовок.

Технологии, использованные в модели ASICS GLIDERIDE 2:

• AHAR+. Это наиболее тонкая облегченная модификация АХАР. Обеспечивает продление срока службы обуви. AHAR+ расположена в участках подошвы, подверженных повышенному износу.

• Dynamic DuoMax. Двойная плотность. Промежуточная подошва, состоящая из двух материалов, различающихся уровнем плотности, вместе с увеличенным экзоскелетным зажимом пятки предназначена для деликатной корректировки положения стопы при избыточном уровне пронации.

• FlyteFoam. Flytefoam обеспечивает необходимую амортизацию, удобство, сниженный вес и отсутствие изгибов при становлении на ровную поверхность.

• Rearfoot and Forefoot GEL. Гель в носочной и пяточной части. Вставки ASICS GEL на основе силикона в промежуточной подошве обеспечивает поглощение удара.

• GUIDESOLE. Новая геометрия подошвы, созданная ASICS. В ней сочетаются основные элементы, обеспечивающие снижение потери энергии в области голеностопного сустава. Мягкая изогнутая конструкция, двухслойная подошва, сохраняющая жесткость и объемная направляющая линия, которая смещает центр тяжести кроссовок к пятке (3D GUIDANCE LINE)

Беговые кроссовки Asics GlideRide 2 отлично подойдут для ежедневных и длительных пробежек. Верх из функциональной сетки обеспечивает превосходную вентиляцию и адаптируется к естественной форме стопы при движении. Уникальная подошва с технологией Guidesole обеспечивает динамичный перекат с пятки на носок и повышает эффективность бегуна, благодаря снижению потери энергии в голеностопном суставе.',
 'ASICS GLIDERIDE 2 running shoes

The GLIDERIDE ™ 2 Running Shoes help you save energy on long runs. This shoe reduces energy expenditure in all phases of the running cycle from landing to take off, helping you cover greater distances than ever before. This season, the shoe`s energy savings are maintained and the movement is smoother and the fit of the upper is improved. Designed for runners looking to feel more energized after long runs, they are ideal for recovery running.

This model features a functional mesh that improves the fit of the upper and provides superior ventilation while adapting to the natural shape of the foot as you move. 3D printed seamless elements create support where you need it without adding weight to your sneaker.

GUIDESOLE ™ technology features a stiff forefoot and a curved outsole that enhances running performance by reducing foot strain. The cushioning FLYTEFOAM ™ technology is located in the upper midsole layer, while the FLYTEFOAM ™ Propel technology lower layer provides more adaptive rebound.

Between these two layers, a rigid plate sits in the forefoot, which ensures the integrity of the midsole. The forward roll motion of the foot pushes the foot and provides smoother, more efficient transitions between strides. In addition, an AHARPLUS ™ rubber insert in the heel of the outsole improves durability and extends the shoe`s lifespan.

Technologies used in the ASICS GLIDERIDE 2 model:

• AHAR +. This is the thinnest lightweight modification of the AHAR. Provides extended shoe life. AHAR + is located in the areas of the outsole that are prone to increased wear.

• Dynamic DuoMax. Double density. The midsole, composed of two materials with different levels of density, together with an enlarged exoskeletal heel clamp, is designed to delicately correct the position of the foot in case of excessive pronation.

• FlyteFoam. Flytefoam provides the cushioning, comfort, reduced weight and no flex when standing on a level surface.

• Rearfoot and Forefoot GEL. Gel in the forefoot and heel. Silicone-based ASICS GEL midsole for shock absorption.

• GUIDESOLE. New outsole geometry created by ASICS. It combines key elements to reduce energy loss in the ankle area. Soft, curved construction, dual-layer outsole that retains rigidity and a voluminous guidance line that shifts the shoe`s center of gravity towards the heel (3D GUIDANCE LINE)

The Asics GlideRide 2 running shoe is perfect for daily and long runs. The functional mesh upper provides superior breathability and adapts to the natural shape of the foot as you move. Unique outsole with Guidesole technology provides dynamic heel-to-toe rolling and improves runner performance by reducing energy loss in the ankle.',
 'Asics_GlideRide_2.png', 'black/green', 389.00, 1);

INSERT INTO shoes
VALUES (4, 'Триатлонки мужские', 'Men`s triathlon', 'Asics', 'Gel-Noosa Tri 12 Blue', 'Беговые кроссовки Asics Gel Noosa Tri 12 выполнены из сочетания сетчатого текстиля и искусственной кожи, и предназначены для быстрых тренировок и соревнований. Вставка Propulsion Trusstic помогает в перекате ноги по поверхности. Данная обувь отлично подойдет для триатлона.

Верх выполнен из сетки Openmesh для максимальной воздухопроницаемости

Эластичные шнурки Caterpy обеспечивают комфортную фиксацию

Усиленный задник для дополнительной поддержки

Промежуточная подошва из вспененного материала FlyteFoam для дополнительной амортизации

Asics Гель в пятке снижает нагрузку на позвоночник, пятки и колени спортсмена

Резиновая подошва с протектором для надежного сцепления с поверхностью',
 'The Asics Gel Noosa Tri 12 running shoe is crafted from a mix of mesh and faux leather for fast workouts and competition. The Propulsion Trusstic insert helps the foot roll over the surface. This shoe is perfect for triathlon.

Openmesh upper for maximum breathability

Caterpy elastic laces provide a comfortable fit

Reinforced heel counter for added support

FlyteFoam midsole for extra cushioning

Asics Heel Gel reduces stress on the athlete`s spine, heels and knees

Rubber outsole with tread for secure traction',
 'Asics_Gel-Noosa_Tri_12_Blue.jpg', 'blue', 274.00, 1);

INSERT INTO shoes
VALUES (5, 'Кроссовки для бега мужские', 'Running shoes for men', 'Asics', 'GT-2000 9 black/blue', 'Беговые кроссовки Asics Gt 2000 9 - легкая, стабилизирующая и с отличной амортизацией обувь для бегунов всех уровней, от любителей до профессионалов. Облегченная средняя часть подошвы с технологией FlyteFoam обеспечивает мягкое приземление после каждого шага. Обновленная технология Asics Gel и более мягкая секция в области пятки для повышения комфорта и амортизации.

Верх кроссовок обновлен и теперь выполнен цельной конструкцией из сетки для лучшей посадки на стопе. Новый материал верха укреплен в основных зонах для усиленной поддержки на каждом этапе всего цикла бега, что обеспечивает более эффективную фазу отрыва.

Верхняя часть выполнена из легкой функциональной сетки для улучшенной воздухопроницаемости

Технология промежуточной подошвы из легкой пены дарит легкое и отзывчивое движение во время бега. Благодаря низкой плотности материала технология FLYTEFOAM помогает достичь максимальной скорости

Сочетание технологий Dynamic Duomax и Trusstic System в средней части подошвы для стабильности и поддержки

Гелевые вставки в пятке и передней части стопы обеспечивают плавное погашение ударных нагрузок на твердой поверхности',
 'The Asics Gt 2000 9 Running Shoe is lightweight, stabilized and cushioned for all levels of runner, from amateur to professional. A lightweight midsole with FlyteFoam technology ensures a soft landing after every stride. Updated Asics Gel technology and a softer heel section for increased comfort and cushioning.

The upper has been updated with a one-piece mesh construction for a better fit on the foot. The new upper material is reinforced in the core zones for increased support at every stage of the entire run for a more efficient lift-off phase.

- Upper made of lightweight functional mesh for improved breathability

Lightweight foam midsole technology delivers lightweight, responsive movement as you run. Thanks to its low material density, FLYTEFOAM technology helps to achieve maximum speed

Combination of Dynamic Duomax and Trusstic System technologies in the midsole for stability and support

Gel inserts in the heel and forefoot provide smooth shock absorption on a hard surface',
 'Asics_GT-2000_9_black-blue.jpg', 'black-blue', 269.00, 1);

INSERT INTO shoes
VALUES (6, 'Кроссовки для бега мужские', 'Running shoes for men', 'Saucony', 'Omni 19 Fog/Citrus', 'Беговые кроссовки Saucony Omni 19 предлагает умеренную поддержку. Модель разработана для ежедневных пробежек или для прогулок. Благодаря особой технологии изготовления верха и классической шнуровке, обувь стабильно ведет себя во время бега. Подошва выполнена по технологии Formfit: три слоя адаптивных амортизирующих пен, работают как единый инструмент амортизации и поддержки. Промежуточная подошва выполнена из специального материала Everun, который качественно гасит ударные нагрузки и при этом сохраняет энергию для отталкивания. Верх кроссовок из легкой и хорошо вентелируемой сетки соответствует контуру стопы.

Особенности:

Подошва выполнена по трёхслойной технологии Formfit: стелька + слой Everun + слой отформованный под форму ноги.

Подошва TRI-FLEX со специально разработанным узором для лучшей гибкости и сцепления с поверхностью. Обеспечивает плотное прилегание к твердому и ровному покрытию.

Специальная сетка обеспечивает структуру или эластичность там, где это необходимо, и сохраняет при этом достаточную воздухопроницаемость.

Высота пятка-носок до 8 мм - такое конструктивное исполнение способствует приземлению с акцентом на всю стопу вместо пятки, и, при должной подготовке и технике бега, к эффективному использованию естественных механизмов амортизации и энергетики мышц и связок ноги.

Специальная вставка TPU на внутренней поверхности подошвы плавно выводит стопу на правильное отталкивание в начале каждого очередного шага.',
 'The Saucony Omni 19 running shoe offers moderate support. Designed for daily jogging or walking. Thanks to the special technology of the upper and the classic lacing, the shoes behave stably while running. The outsole is made with Formfit technology: three layers of adaptive cushioning foam that work as a single tool for cushioning and support. The midsole is made of special Everun material, which absorbs shock loads and retains energy for repulsion. Made from lightweight, well-ventilated mesh, the upper conforms to the contour of the foot.

Features:

The sole is made using three-layer Formfit technology: insole + Everun layer + molded layer to fit the shape of the foot.

TRI-FLEX outsole with a specially designed pattern for better flexibility and traction. Provides a snug fit on a hard and even surface.

The special mesh provides structure or elasticity where needed while maintaining adequate breathability.

Heel-toe height up to 8 mm - this design contributes to landing with an emphasis on the entire foot instead of the heel, and, with proper preparation and running technique, to the effective use of the natural mechanisms of shock absorption and energy of the muscles and ligaments of the leg.

A special TPU insert on the inner surface of the sole smoothly guides the foot into the correct take-off at the beginning of each next step. ',
 'Saucony_Omni_19_Fog-Citrus.jpg', 'grey', 324.00, 1);

INSERT INTO shoes
VALUES (7, 'Кроссовки для бега мужские', 'Running shoes for men', 'Saucony', 'Kinvara 12 Future/Black', 'Saucony Kinvara 12 переработан, и в нем все, что вам нужно для комфортного бега. Благодаря плавному ходу, легкому дизайну и мягкой, но отзывчивой амортизации бегуны могут взять эту обувь независимо от того, какую тренировку они запланировали.

Мягкие кроссовки.
Амортизация PWRRUN поддерживает легкую отзывчивость, что делает эту обувь фаворитом для ежедневных тренировок на много миль или бега на длинные дистанции.
Дополнительный контакт с землей добавляет мощности гибкой езде, делая переход от приземления к взлету еще быстрее.
Легкая печать придает дышащему адаптируемому сетчатому верху достаточную структуру, чтобы чувствовать себя в безопасности, не отягощая вас.',
 'The Saucony Kinvara 12 is redesigned with everything you need for a comfortable run. With a smooth ride, lightweight design, and soft yet responsive cushioning, runners can take this shoe no matter what kind of workout they`ve planned.

Soft sneakers.
PWRRUN cushioning maintains lightweight responsiveness, making this shoe a favorite for daily long distance workouts or long distance running.
The extra ground contact adds power to the agile ride, making the transition from touchdown to takeoff even faster.
Lightweight printing gives the breathable, adaptable mesh upper enough structure to feel secure without weighing you down.',
 'Saucony_Kinvara_12_Future-Black.jpg', 'black', 324.00, 1);