import axios from "axios";

export function getData(url, data) {
    axios
        .get(url)
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
        });
}

export function postData(url, data) {
    axios
        .post(url, data, {
            headers: {
                "Content-Type": "application/json",
            },
        })
        .then((response) => {
            return response;
        })
        .catch((error) => {
            console.log(error);
        });
}
