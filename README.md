# 🚀 Projeto CI/CD com GitHub Actions - Spring Boot

Este repositório contém uma aplicação Java com Spring Boot que utiliza uma pipeline de CI/CD automatizada com **GitHub Actions**, realizando deploys em ambientes de **desenvolvimento** e **produção**, com validações de código e mecanismo de rollback.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- GitHub Actions
- SSH + SCP + Tmux
- VPS com Ubuntu 24.04
- Health Check HTTP

---

## 📁 Estrutura da Pipeline

### 🔁 Workflows

#### 1. **CI/CD - Desenvolvimento**
> 📂 `.github/workflows/deploy-dev.yml`

- **Gatilho**: Ao abrir um Pull Request na branch `develop`
- **Etapas**:
  - Build do projeto com Maven
  - Envio do JAR via `scp` para a VPS
  - Execução com `tmux` na porta 8081 com perfil `local`
  - Verificação de Health Check
  - Log de execução
- ✅ Permite validação e testes em ambiente separado antes de ir para produção.

---

#### 2. **CI - Validação de Código**
> 📂 `.github/workflows/code-check.yml`

- **Gatilho**: Ao abrir um Pull Request na branch `main`
- **Etapas**:
  - Build do projeto com Maven
  - Execução de testes automatizados
  - Análise de código (ex: validação de style/lint)

---

#### 3. **CD - Produção com Rollback**
> 📂 `.github/workflows/deploy-prod.yml`

- **Gatilho**: Ao fazer `push` na branch `main`
- **Etapas**:
  - Build do projeto com Maven
  - Envio do JAR para a VPS
  - Parada da aplicação anterior
  - Execução do novo JAR via `tmux` (porta 8080, perfil `prod`)
  - Verificação do Health Check (ex: `/actuator/health`)
  - ✅ Se sucesso: logs e finalização
  - ❌ Se falha: rollback automático para o JAR anterior

---

## 🔐 Secrets Utilizados

Configure os seguintes secrets no GitHub:

| Nome                  | Descrição                          |
|-----------------------|------------------------------------|
| `VPS_HOST`            | Endereço IP ou domínio da VPS      |
| `VPS_USERNAME`        | Usuário de acesso SSH              |
| `VPS_PASSWORD`        | Senha de acesso via `sshpass`      |

---

## ⚙️ Requisitos da VPS

- Porta 22 liberada (SSH)
- Java 17 instalado
- Diretório `/home/<usuário>/app` disponível
- `tmux`, `sshpass`, `scp`, `curl` instalados

---

## 📊 Monitoramento

- A aplicação possui endpoint de health check (`/actuator/health`)
- Logs disponíveis via `tmux` ou redirect para arquivo `logs.txt`

---

## 📌 Observações

- O nome do JAR é gerado dinamicamente no momento do build.
- É possível realizar rollback automático em produção, garantindo resiliência em falhas.

---

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

