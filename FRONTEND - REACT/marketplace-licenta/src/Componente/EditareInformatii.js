import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import base64 from "image-to-base64";
import "./EditareInformatii.css";
import ImageUploader from "react-images-upload";

class EditareInformatii extends Component {
  state = { utilizator: {}, imagineUtilizator: "", salvareSuccess: false };

  constructor(props) {
    super(props);

    this.onDrop = this.onDrop.bind(this);
  }

  changeName = event => {
    this.setState({
      utilizator: {
        ...this.state.utilizator,
        numeUtilizator: event.target.value
      }
    });
  };

  changeUsername = event => {
    this.setState({
      utilizator: {
        ...this.state.utilizator,
        usernameUtilizator: event.target.value
      }
    });
  };

  changeEmail = event => {
    this.setState({
      utilizator: {
        ...this.state.utilizator,
        emailUtilizator: event.target.value
      }
    });
  };

  changeAdresa = event => {
    this.setState({
      utilizator: {
        ...this.state.utilizator,
        adresaUtilizator: event.target.value
      }
    });
  };

  changeTelefon = event => {
    this.setState({
      utilizator: {
        ...this.state.utilizator,
        telefonUtilizator: event.target.value
      }
    });
  };

  getUtilizator = (props = this.props) => {
    const { token } = this.props;

    fetch(`http://localhost:8888/utilizator/findUtilizatorById`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      }
    })
      .then(res => res.json())
      .then(res => this.setState({ utilizator: res }))
      .catch(e => console.log(e));
  };

  onDrop = imagini => {
    this.setState({
      imagineUtilizator: URL.createObjectURL(imagini[imagini.length - 1])
    });
  };

  componentDidMount() {
    this.getUtilizator();
  }

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
  editareCont = async event => {
    event.preventDefault();
    const { imagineUtilizator, utilizator } = this.state;
    const token = localStorage.getItem("token");

    const base64image = await base64(imagineUtilizator);
    const imagePath = await this.uploadImage(base64image);
    const options = {
      method: "PUT",
      body: JSON.stringify({
        ...utilizator,
        imagineUtilizator: imagePath || utilizator.imagineUtilizator
      }),
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      }
    };

    await fetch("http://localhost:8888/utilizator/updateUtilizator", options)
      .then(() => this.setState({ salvareSuccess: true }))
      .catch(err => this.setState({ error: true }));
  };
  render() {
    const { error } = this.state;

    console.log("user vechi: ", this.state.utilizator);

    return (
      <div className="container-mare-editare-profil">
        <h1 className="heading-editare-cont">Editare informatii de profil</h1>
        <div>
          <label className="label-informatie-profil">Nume:</label>
          <input
            value={this.state.utilizator.numeUtilizator}
            onChange={this.changeName}
            type="text"
            name="name"
            className="editare-nume"
          />
        </div>
        <div>
          <label className="label-informatie-profil">Username:</label>
          <input
            value={this.state.utilizator.usernameUtilizator}
            onChange={this.changeUsername}
            type="text"
            username="username"
            className="editare-username"
          />
        </div>
        <div>
          <label className="label-informatie-profil">Email:</label>
          <input
            value={this.state.utilizator.emailUtilizator}
            onChange={this.changeEmail}
            type="text"
            name="email"
            className="editare-email"
          />
        </div>
        <div>
          <label className="label-informatie-profil">Adresa:</label>
          <input
            value={this.state.utilizator.adresaUtilizator}
            onChange={this.changeAdresa}
            type="text"
            name="adresa"
            className="editare-adresa"
          />
        </div>
        <div>
          <label className="label-informatie-profil">Telefon:</label>
          <input
            value={this.state.utilizator.telefonUtilizator}
            onChange={this.changeTelefon}
            type="number"
            name="telefon"
            className="editare-telefon"
          />
        </div>

        <div
          class="imagine-utilizator"
          style={{
            backgroundImage: `url(${this.state.imagineUtilizator ||
              this.state.utilizator.imagineUtilizator})`
          }}
        />

        <div>
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
            <button
              type="submit"
              className="buton-creeaza-cont"
              onClick={this.editareCont}
            >
              Salveaza datele
            </button>
            {this.state.salvareSuccess && <Redirect to="/contulMeu" />}
            {this.state.error && (
              <div className="eroare">Nu s-a putut edita utilizatorul</div>
            )}
          </div>
        </div>
      </div>
    );
  }
}

export default EditareInformatii;
