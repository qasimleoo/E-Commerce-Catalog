INSERT INTO user_role (user_id, role_id)
SELECT 1, 1
WHERE NOT EXISTS (
    SELECT 1 FROM user_role
    WHERE user_id = 1 AND role_id = 1
)
UNION ALL
SELECT 1, 2
WHERE NOT EXISTS (
    SELECT 1 FROM user_role
    WHERE user_id = 1 AND role_id = 2
);