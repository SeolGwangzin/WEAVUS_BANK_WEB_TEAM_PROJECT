INSERT INTO users (
                   username,
                   password,
                   email,
                   full_name,
                   birth_date,
                   gender,
                   postal_code,
                   prefecture,
                   city,
                   address_detail,
                   phone_number
) VALUES (
          'abc12345678',
          'abc12345678',
          '123@gamil.com',
          '田中太郎',
          '2025-07-07',
          '男',
          '1870003',
          '東京都',
          '小平市 花小金井南町',
          '建物1 103号室',
          '01065234512'
         );


INSERT INTO accounts (
                      user_id, account_number, balance, password, purpose
)VALUES(
        1,
        '123456789012',
        100000,
        '1234',
        '給料お貰うため'
       );

INSERT INTO accounts (
    user_id, account_number, balance, password, purpose
)VALUES(
           1,
           '223456789012',
           90000,
           '1234',
           '給料お貰うため'
       );
