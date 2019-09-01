select id      as order_id,
       'order' as source_type,
       amount  as order_total_amount,
       ''      as m_order_id,
       ''      as payment_id,
       ''      as m_payment_amount,
       ''      as logistic_id,
       ''      as logistic_amount
from `order`
union
select mo.id     as order_id,
       'm_order' as source_type,
       ''        as order_total_amount,
       id        as m_order_id,
       ''        as payment_id,
       ''        as m_payment_amount,
       ''        as logistic_id,
       ''        as logistic_amount
from `m_order` as mo
union
select p.order_id as order_id,
       'payments' as source_type,
       ''         as order_total_amount,
       ''         as m_order_id,
       p.id       as payment_id,
       p.amount   as m_payment_amount,
       ''         as logistic_id,
       ''         as logistic_amount
from `payments` as p
union
select l.order_id as order_id,
       'logistic' as source_type,
       ''         as order_total_amount,
       ''         as m_order_id,
       ''         as payment_id,
       ''         as m_payment_amount,
       l.id       as logistic_id,
       l.amount   as logistic_amount
from `logistic` as l



