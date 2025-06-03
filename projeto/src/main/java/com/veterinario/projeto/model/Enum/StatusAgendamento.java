package com.veterinario.projeto.model.Enum;

import jakarta.persistence.*;
import lombok.*;

/**
 * Enum para definir o status do agendamento.
 */
public enum StatusAgendamento {
    AGENDADO,
    CONFIRMADO,
    CANCELADO,
    REALIZADO
}
