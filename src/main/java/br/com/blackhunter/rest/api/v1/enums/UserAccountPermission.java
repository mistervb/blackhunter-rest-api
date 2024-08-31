package br.com.blackhunter.rest.api.v1.enums;

public enum UserAccountPermission {
    CREATE_OWN,        // Criar suas próprias coisas
    DELETE_OWN,        // Deletar suas próprias coisas
    EDIT_OWN,          // Editar suas próprias coisas
    READ_ALLOWED,      // Ler coisas permitidas a ele
    CREATE_CONTENT,    // Criar conteúdo específico (ex: vídeos)
    DELETE_CONTENT,    // Deletar seu próprio conteúdo
    EDIT_CONTENT,      // Editar seu próprio conteúdo
    MANAGE_COMMUNITY,  // Gerenciar comunidade (Curador)
    FULL_ACCESS;       // Acesso completo (Owner)


}
