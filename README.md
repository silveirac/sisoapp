<div align="center">
    <h1>💻 Siso App 🖥️</h1>
    <h6>BackEnd I - Turma 1 - Bimestre 5</h6>
</div>

---
<div  align="center">
<nav> <a href="#objetivos">Objetivos</a> | <a href="#estrutura">Estrutura</a> | <a href="#integrantes">Integrantes</a></nav>
</div>

---

<br>
<h2 id="objetivos">🚀 Objetivos 🚀</h2>
<p>


Este projeto consiste em um aplicativo de gerenciamento de consultório odontológico, dentro do qual podemos realizar as seguintes atividades:


- Visualizar, Editar, Excluir e Adicionar Paciêntes.
- Visualizar, Editar, Excluir e Adicionar Dentistas.
- Visualizar, Editar, Excluir e Adicionar Consultas.
- Visualizar, Editar, Excluir e Adicionar Usuários.

---

<h2 id="estrutura">🎯 Estrutura de pastas ⚒️</h2>

Iremos seguir parte do que está proposto e iremos utilizar dependencias do bootspring (Em Construção) Com eles é possível simplificar o código, que podem ser reutilizados em diversos lugares e contextos.

Assim, podemos criá-los com responsabilidades determinadas e utilizar em conjunto com outros módulos para realizar tarefas mais complexas.

A disposição das pastas e aquivos segue o padrão:


:open_file_folder: Back-End
- :open_file_folder: Config
- :page_facing_up: AppConfig
- :page_facing_up: SegurityConfig
- :page_facing_up: WebConfig
- :open_file_folder: Controller
- :page_facing_up: ConsultaController
- :page_facing_up: DenstistaController
- :page_facing_up: PacienteController
- :page_facing_up: UsuarioController
- :open_file_folder: dto
- :page_facing_up: DenstistaRequest
- :page_facing_up: DesntistaResponse
- :page_facing_up: EnderecoRequest
- :page_facing_up: EnderecoResponse
- :page_facing_up: PacienteRequest
- :page_facing_up: PacienteResponse
- :page_facing_up: UsuarioRequest
- :page_facing_up: UsuarioResponse
- :open_file_folder: Model
- :page_facing_up: Consulta
- :page_facing_up: Dentista
- :page_facing_up: Endereco
- :page_facing_up: Paciente
- :page_facing_up: Usuario
- :open_file_folder: Repository
- :page_facing_up: IConsulta
- :page_facing_up: IDentista
- :page_facing_up: IEndereco
- :page_facing_up: IPaciente
- :page_facing_up: IUsuario
- :open_file_folder: Segurity
- :page_facing_up: AutenticacaoService
- :page_facing_up: SegurityService
- :page_facing_up: TokenDTO
- :page_facing_up: TokenService
- :page_facing_up: UsuarioRole
- :open_file_folder: Service
- :page_facing_up: ConsultaService
- :page_facing_up: DentistaService
- :page_facing_up: EnderecoService
- :page_facing_up: PacienteService
- :page_facing_up: UsuarioService

- :page_facing_up: SisoappApplication

- :open_file_folder: util
- :page_facing_up: Util

- :open_file_folder: Test
- :page_facing_up: ConsultaControllerTest
- :page_facing_up: DentistaTest
- :page_facing_up: PacienteControllerTest
- :page_facing_up: UsuarioControllerTest

Como dito acima, módulos são recursos muito úteis, uma vez que proporcionam qualidade e eficiência maior do código produzido, além de proporcionar agilidade no desenvolvimento de novas funcionalidades. Tudo isto devido a não ser necessário duplicar ou triplicar o mesmo bloco para utilizar em diferentes lugares. 


---


<h2>📑 Arquiteura do Projeto 📑</h2>

**Back-End:**

- [x] Em construção;


---


<h2 id="integrantes">✅Integrantes do grupo ✅</h2>


<a href="https://github.com/silveirac">
<img src="https://avatars.githubusercontent.com/u/99031403?v=4" height="50px" style="border-radius: 50px"> 
Cauê Silveira
</a>

<br>

<a href="https://github.com/Rashield">
<img src="https://avatars.githubusercontent.com/u/8429910?v=4" height="50px" style="border-radius: 50px"> 
Darlei Feix 
</a>

<br>

<a href="https://github.com/paulinhodeveloper">
<img src="https://avatars.githubusercontent.com/u/99299721?v=4" height="50px" style="border-radius: 50px"> 
Paulo Henrique Santos Borges
</a>

<br>

<a href="https://github.com/Mineiroc4">
<img src="https://avatars.githubusercontent.com/u/94484188?v=4" height="50px" style="border-radius: 50px"> 
Rafael Varela 
</a>

<br>

<a href="https://github.com/schelen01">
<img src="https://avatars.githubusercontent.com/u/88978043?v=4" height="50px" style="border-radius: 50px"> 
Schelen Grossel
</a>

<br>
