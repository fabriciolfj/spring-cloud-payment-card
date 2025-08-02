-- Script para criar as tabelas no PostgreSQL

-- Criar tabela payment
CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    card_token VARCHAR(255) NOT NULL,
    customer_id VARCHAR(255) NOT NULL,
    order_id VARCHAR(255) NOT NULL,
    date_update TIMESTAMP,
    date_create TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- Criar tabela payment_logs
CREATE TABLE payment_logs (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(50),
    date_update TIMESTAMP,
    date_create TIMESTAMP,
    payment_id BIGINT NOT NULL,
    version BIGINT DEFAULT 0,
    CONSTRAINT fk_payment_logs_payment
        FOREIGN KEY (payment_id)
        REFERENCES payment(id)
        ON DELETE CASCADE
);

-- Criar índices para melhor performance
CREATE INDEX idx_payment_code ON payment(code);
CREATE INDEX idx_payment_customer_id ON payment(customer_id);
CREATE INDEX idx_payment_order_id ON payment(order_id);
CREATE INDEX idx_payment_logs_payment_id ON payment_logs(payment_id);
CREATE INDEX idx_payment_logs_status ON payment_logs(status);

-- Comentários nas tabelas
COMMENT ON TABLE payment IS 'Tabela de pagamentos';
COMMENT ON TABLE payment_logs IS 'Tabela de logs dos pagamentos';

COMMENT ON COLUMN payment.code IS 'Código único do pagamento';
COMMENT ON COLUMN payment.amount IS 'Valor do pagamento';
COMMENT ON COLUMN payment.card_token IS 'Token do cartão';
COMMENT ON COLUMN payment.customer_id IS 'ID do cliente';
COMMENT ON COLUMN payment.order_id IS 'ID do pedido';
COMMENT ON COLUMN payment.version IS 'Controle de versão para lock otimista';

COMMENT ON COLUMN payment_logs.status IS 'Status do pagamento (enum)';
COMMENT ON COLUMN payment_logs.payment_id IS 'Referência ao pagamento';
COMMENT ON COLUMN payment_logs.version IS 'Controle de versão para lock otimista';