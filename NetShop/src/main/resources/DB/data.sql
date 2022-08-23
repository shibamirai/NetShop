INSERT INTO `items` (`id`, `name`, `image`, `price`, `description`, `is_deleted`, `create_date_time`, `update_date_time`) VALUES
(1, 'いちご', 'いちご.jfif', 300, '粒が大きく、ジューシーなイチゴです', 0, '2022-08-10 11:04:56', '2022-08-19 14:23:20');
INSERT INTO `items` (`id`, `name`, `image`, `price`, `description`, `is_deleted`, `create_date_time`, `update_date_time`) VALUES
(2, 'パイナップル', 'パインナップル.jfif', 350, 'ハワイ産', 0, '2022-08-10 11:04:56', '2022-08-19 14:23:20');
INSERT INTO `items` (`id`, `name`, `image`, `price`, `description`, `is_deleted`, `create_date_time`, `update_date_time`) VALUES
(3, 'バナナ', 'バナナ.jfif', 150, '高原栽培の糖度が非常に高いバナナです。', 0, '2022-08-10 11:04:56', '2022-08-19 14:23:20');
INSERT INTO `items` (`id`, `name`, `image`, `price`, `description`, `is_deleted`, `create_date_time`, `update_date_time`) VALUES
(4, 'マンゴー', 'マンゴー.jfif', 300, '太陽のタマゴ', 0, '2022-08-10 11:04:56', '2022-08-19 14:23:20'),
(5, 'リンゴ', 'リンゴ.jfif', 100, '王林', 0, '2022-08-10 11:04:56', '2022-08-19 14:23:20'),
(6, '桃', '桃.jfif', 450, '桃太郎が生まれます', 0, '2022-08-10 11:04:56', '2022-08-19 14:23:20');

INSERT INTO `stocks` (`id`, `item_id`, `quantity`) VALUES
(1, 1, 10);
INSERT INTO `stocks` (`id`, `item_id`, `quantity`) VALUES
(2, 2, 10);
INSERT INTO `stocks` (`id`, `item_id`, `quantity`) VALUES
(3, 3, 10);
INSERT INTO `stocks` (`id`, `item_id`, `quantity`) VALUES
(4, 4, 10),
(5, 5, 10),
(6, 6, 10);
