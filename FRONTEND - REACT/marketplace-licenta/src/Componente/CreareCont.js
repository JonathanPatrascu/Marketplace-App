import React, { Component } from "react";
import ImageUploader from "react-images-upload";
import "./CreareCont.css";
import base64 from "image-to-base64";

class CreareCont extends Component {
  state = {
    numeUtilizator: "",
    usernameUtilizator: "",
    parolaUtilizator: "",
    orasUtilizator: "",
    adresaUtilizator: "",
    telefonUtilizator: "",
    emailUtilizator: "",
    imagineUtilizator: "",
    token: null,
    rolUtilizator: { idRolUtilizator: 1 },
    error: false
  };

  constructor(props) {
    super(props);

    this.onDrop = this.onDrop.bind(this);
  }

  onDrop = imagini => {
    this.setState({
      imagineUtilizator: URL.createObjectURL(imagini[imagini.length - 1])
    });
  };

  changeName = event => {
    this.setState({ numeUtilizator: event.target.value });
  };
  changeEmail = event => {
    this.setState({ emailUtilizator: event.target.value });
  };

  changeUsername = event => {
    this.setState({ usernameUtilizator: event.target.value });
  };

  changeParola = event => {
    this.setState({ parolaUtilizator: event.target.value });
  };

  changeAdresa = event => {
    this.setState({ adresaUtilizator: event.target.value });
  };

  changeJudet = event => {
    this.setState({ orasUtilizator: event.target.value });
  };

  changeTelefon = event => {
    this.setState({ telefonUtilizator: event.target.value });
  };
  verificareLungime = object => {
    if (object.target.value.length > object.target.maxLength)
      object.target.value = object.target.value.slice(
        0,
        object.target.maxLength
      );
  };

  uploadImage = async image =>
    fetch("https://api.imgur.com/3/image", {
      method: "POST",
      body: JSON.stringify({
        image
      }),
      headers: {
        "Content-Type": "application/json",
        Authorization: `Client-ID f95514a5d23f091`
      }
    })
      .then(res => res.json())
      .then(res => res.data.link);

  inregistrareUtilizator = async event => {
    event.preventDefault();

    const { token, imagineUtilizator, error, ...user } = this.state;

    const base64image = await base64(imagineUtilizator);
    const imagePath = await this.uploadImage(base64image);

    const options = {
      method: "POST",
      body: JSON.stringify({
        ...user,
        imagineUtilizator: imagePath || ""
      }),
      headers: {
        "Content-Type": "application/json"
      }
    };

    await fetch("http://localhost:8888/utilizator/saveUser", options)
      .then(() => this.props.history.push("/login"))
      .catch(err => {
        this.setState({ error: true });
      });
  };

  render() {
    const {
      numeValue,
      emailValue,
      usernameValue,
      parolaValue,
      adresaValue,
      judetValue,
      telefonValue,
      imagineUtilizator,
      error
    } = this.state;

    return (
      <div className="register">
        <h1 className="heading-inregistrare">Inregistrare</h1>
        <form action="inregistrare" onSubmit={this.inregistrareUtilizator}>
          <input
            value={numeValue}
            onChange={this.changeName}
            type="text"
            name="name"
            required
            placeholder="Nume / Prenume"
          />
          <br />
          <input
            value={emailValue}
            onChange={this.changeEmail}
            type="text"
            name="email"
            required
            placeholder="Email"
          />
          <br />
          <input
            value={usernameValue}
            onChange={this.changeUsername}
            type="text"
            username="username"
            required
            placeholder="Username"
          />
          <br />
          <input
            value={parolaValue}
            onChange={this.changeParola}
            type="password"
            name="parola"
            required
            placeholder="Parola"
          />
          <br />
          <input
            value={adresaValue}
            onChange={this.changeAdresa}
            type="text"
            name="adresa"
            required
            placeholder="Adresa: Strada, numar, Cod postal"
          />
          <br />
          <select
            onChange={this.changeJudet}
            value={judetValue}
            name="judet"
            id="1"
            className="judexBox"
          >
            <option value="Alba">Alba</option>
            <option value="Arad">Arad</option>
            <option value="Arges">Arges</option>
            <option value="Bacau">Bacau</option>
            <option value="Bihor">Bihor</option>
            <option value="Bistrita-Nasaud">Bistrita-Nasaud</option>
            <option value="Botosani">Botosani</option>
            <option value="Braila">Braila</option>
            <option value="Brasov">Brasov</option>
            <option value="Bucrresti">Bucrresti</option>
            <option value="Buzau">Buzau</option>
            <option value="Calarasi">Calarasi</option>
            <option value="Caras Severin">Caras Severin</option>
            <option value="Cluj">Cluj</option>
            <option value="Constanta">Constanta</option>
            <option value="Covasna">Covasna</option>
            <option value="Dambovita">Dambovita</option>
            <option value="Dolj">Dolj</option>
            <option value="Galati">Galati</option>
            <option value="Giurgiu">Giurgiu</option>
            <option value="Gorj">Gorj</option>
            <option value="Harghita">Harghita</option>
            <option value="Hunedoara">Hunedoara</option>
            <option value="Ialomita">Ialomita</option>
            <option value="Iasi">Iasi</option>
            <option value="v">Ilfov</option>
            <option value="Maramures">Maramures</option>
            <option value="Mehedinti">Mehedinti</option>
            <option value="Mures">Mures</option>
            <option value="Neamt">Neamt</option>
            <option value="Olt">Olt</option>
            <option value="Prahova">Prahova</option>
            <option value="Salaj">Salaj</option>
            <option value="Satu Mare">Satu Mare</option>
            <option value="Sibiu">Sibiu</option>
            <option value="Suceava">Suceava</option>
            <option value="Teleorman">Teleorman</option>
            <option value="Timis">Timis</option>
            <option value="Tulcea">Tulcea</option>
            <option value="Valcea">Valcea</option>
            <option value="Vaslui">Vaslui</option>
            <option value="Vrancea">Vrancea</option>
          </select>
          <br />
          <input
            value={telefonValue}
            onChange={this.changeTelefon}
            type="number"
            name="telefon"
            required
            placeholder="Telefon"
            maxLength="10"
            onInput={this.verificareLungime}
          />
          <br />
          {imagineUtilizator && (
            <div
              class="imagine-utilizator"
              style={{ backgroundImage: `url(${imagineUtilizator})` }}
            />
          )}
          <div className="imagine-text">Incarca o imagine:</div>
          <ImageUploader
            withIcon={true}
            buttonText="Choose images"
            onChange={this.onDrop}
            imgExtension={[".jpg", ".gif", ".png", ".gif"]}
            maxFileSize={5242880}
          />
          {error && <div>Contul nu a fost creat</div>}
          <div className="div-creare-cont">
            <button type="submit" className="buton-creeaza-cont">
              Creeaza cont
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default CreareCont;
