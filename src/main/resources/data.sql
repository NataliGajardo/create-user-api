-- Insert data in user_data
INSERT INTO user_data (id, username, password, email, is_active, creation_date, modification_date, last_login) VALUES
  ('1e4469d4-4136-4d5a-9316-c3f62fc3c5a9', 'user1', 'password1', 'user1@example.com', true, '2023-01-01T10:00:00', '2023-01-02T12:00:00', '2023-01-03T14:00:00'),
  ('2f5369d4-1236-4d5a-9816-d3f72fc3d6b9', 'user2', 'password2', 'user2@example.com', true, '2023-02-01T11:00:00', '2023-02-02T13:00:00', '2023-02-03T15:00:00'),
  ('3a6379d4-2236-4d5a-8216-e3a83fc4f7c9', 'user3', 'password3', 'user3@example.com', false, '2023-03-01T09:00:00', '2023-03-02T11:00:00', '2023-03-03T13:00:00');

-- Insert data in phone_data related with user_data
INSERT INTO phone_data (id, user_id, number, citycode, countrycode) VALUES
  (RANDOM_UUID(), '1e4469d4-4136-4d5a-9316-c3f62fc3c5a9', '1234567', '1', '57'),
  (RANDOM_UUID(), '1e4469d4-4136-4d5a-9316-c3f62fc3c5a9', '2345678', '1', '57'),
  (RANDOM_UUID(), '2f5369d4-1236-4d5a-9816-d3f72fc3d6b9', '3456789', '2', '1'),
  (RANDOM_UUID(), '3a6379d4-2236-4d5a-8216-e3a83fc4f7c9', '4567890', '3', '44');
