
INSERT INTO codigo_secuencia (prefijo, ultimo_valor)
SELECT *
FROM (
    SELECT 'BOL', 0
) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM codigo_secuencia LIMIT 1
);