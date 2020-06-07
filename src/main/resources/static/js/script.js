"user strict";

document.addEventListener("DOMContentLoaded", (event) => {
    event.preventDefault();
    const makeGetRequest = async () => {
        await fetch('http://localhost:8088/api/securities', {
                method: 'GET',
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                }
        })
        .then(response => response.json())
        .then(json => {
            data(json);
        })
        .catch(response => {
            console.log(response);
        });
    };
    setTimeout(makeGetRequest,2000);
    
    
    const data = json => {
        getTableHeader(Object.keys(json[0]));
        getTableData(json);
        disableBlur();
    };

    const columnNames = ["uuid", "дата", "инструмент", "стоимость"];

    const getTableHeader = fields => {
        console.log(fields);
        const table = document.querySelector(".table");
        const thead = document.createElement("thead");
        const tr = document.createElement("tr");
        const fragment = document.createDocumentFragment();
        let count = 0;
        fields.forEach(item => {
            if (item === "uuid") {
                const th = document.createElement("th");
                th.textContent = columnNames[count++];
                th.scope = "col";
                th.setAttribute("style", "display: none");
                fragment.append(th);
            }
            const th = document.createElement("th");
            th.textContent = columnNames[count++];
            th.scope = "col";
            fragment.append(th);
        });
        tr.append(fragment);
        thead.append(tr);
        table.append(thead);
    };

    const getTableData = data => {
        console.log(data);
        const table = document.querySelector(".table");
        const tbody = document.createElement("tbody");
        data.forEach(x => {
            const tr = document.createElement("tr");
            const fragment = document.createDocumentFragment();
            const keys = Object.keys(data[0]);
            keys.forEach(y => {
                let td;
                if (y === "uuid") {
                    td = document.createElement("td");
                    td.scope = "row";
                    td.setAttribute("style", "display: none");
                } else {
                    td = document.createElement("td");
                    td.setAttribute("contentEditable", true);
                    td.setAttribute("data-key","content"); 
                    td.setAttribute("uniqueId",getUniqueName());
                }
                td.textContent = x[y];
                fragment.append(td);
            });
            tr.append(fragment);
            tbody.append(tr);
            table.append(tbody);
            
        });
        grapth();
    };


// ========================================================================================================

    // Add modal form
    const btn = document.querySelector("[data-add]");
    const modal = document.querySelector(".modal");

    btn.addEventListener("click", openModalLogic);

    function openModalLogic() {
        modal.classList.add("show");
        modal.classList.remove("hide");
        document.body.style.overflow = "hidden";
    }

    modal.addEventListener("click", (event) => {
        if (event.target === modal || event.target.getAttribute("data-close") === "") {
            modal.classList.add("hide");
            modal.classList.remove("show");
            document.body.style.overflow = "";
        }
    });

    document.addEventListener("keydown", (event) => {
        if (event.code === "Escape" && modal.classList.contains("show")) {
            modal.classList.add("hide");
            modal.classList.remove("show");
            document.body.style.overflow = "";
        }
    });

    const forms = document.querySelectorAll("form");

    const messageStorage = {
        loading: "img/form/spinner.svg",
        success: "Данные успешно отправлены!",
        failure: "Что-то пошло не так("
    };

    forms.forEach(item => {
        postData(item);
    });


// ========================================================================================================

    // POST REQUEST
    const makePostRequest = async (url, data) => {
        const res = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: data
        });
        if (!res.ok) {
            throw new Error(`Could not fetch ${url}, status ${res.status}`);
        }
        return await res.json();
    };


// ========================================================================================================

    // FORM
    function postData(form) {
        form.addEventListener("submit", (event) => {
            event.preventDefault();

            const statusMessage = document.createElement("img");
            statusMessage.classList.add("spinner__formatting");
            statusMessage.setAttribute("src", messageStorage.loading);
            form.insertAdjacentElement("afterend", statusMessage);

            const formData = new FormData(form);

            const object = {};
            formData.forEach(function (value, key) {
                object[key] = value;
            });
            const jsonFormatedData = JSON.stringify(object);

            makePostRequest("http://localhost:8088/api/securities", jsonFormatedData)
                .then(data => {

                    showNotification(messageStorage.success);

                    const value = Object.entries(data);
                    const array = value.map(item => item[1]);

                    const table = document.querySelector("tbody");
                    const tr = document.createElement("tr");
                    const fragment = document.createDocumentFragment();

                    let count = 0;
                    array.forEach(item => {
                        if (count === 0) {
                            const td = document.createElement("td");
                            td.setAttribute("contentEditable", true);
                            td.setAttribute("style", "display: none");
                            td.textContent = item;
                            fragment.append(td);
                            count = 1;
                        } else {
                            const td = document.createElement("td");
                            td.setAttribute("contentEditable", true);
                            td.setAttribute("data-key","content");
                            td.setAttribute("uniqueId",getUniqueName());
                            td.textContent = item;
                            fragment.append(td);
                        }
                    });
                    tr.append(fragment);
                    table.append(tr);
                    grapth();
                    disableBlur();
                })
                .catch(() => {
                    showNotification(messageStorage.failure);
                }).finally(() => {
                    form.reset();
                    statusMessage.remove();
                });
        });
    }


// ========================================================================================================

    function showNotification(message) {
        const prevModalDialog = document.querySelector(".modal__dialog");
        prevModalDialog.classList.add("hide");
        openModalLogic();

        const thanksModal = document.createElement("div");
        thanksModal.classList.add("modal__dialog");
        thanksModal.innerHTML = `
            <div class="modal__content">
                <div data-close class="modal__close">x</div>
                <div class="modal__title">${message}</div>
            </div>
            `;
        document.querySelector('.modal').append(thanksModal);
        setTimeout(() => {
            thanksModal.remove();
            prevModalDialog.classList.add("show");
            prevModalDialog.classList.remove("hide");

            modal.classList.add("hide");
            modal.classList.remove("show");
            document.body.style.overflow = "";
        }, 2000);
    }
//=========================================================================================================
// PUT request

    const dataPut = {};

    const makePutRequest = async (url, data) => {
        // console.log(data);
        const res = await fetch(url, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: data
        });
        if (!res.ok) { 
            showNotification(messageStorage.failure);
            throw new Error(`Could not fetch ${url}, status ${res.status}`);
        } 
        return await res.json();
    };

    let getUniqueId;
    let obj=[];

    function listData() {
   
        let count = 0;
        const elementChildrens = document.querySelector("tbody");
        for (let node of elementChildrens.childNodes) {
            for (let node2 of node.childNodes){
                if(getUniqueId===node2.getAttribute("uniqueId")){
                    // console.log(node2);
                    console.log(node2.parentElement);
                    for (let node of node2.parentElement.childNodes){
                        console.log(node);
                        obj[count] = node.innerHTML;
                        count++;
                    }
                }
            }
        }  
        // console.log(obj);
        const keys = ['uuid','dateIssue','securityName','price'];
        let i = 0;
        obj.forEach(item => {
            dataPut[keys[i++]] = item;
        });
    }



    setTimeout( ()=> {
        $(function () {
            $("#main").contentEditable().change(function (event) {
                // console.log(event);
                grapth();
                // console.log(event.changedField.context.getAttribute("uniqueId"));
                getUniqueId = event.changedField.context.getAttribute("uniqueId");
                let value = Object.entries(event.changed).map(item => item[1])[0];
                // console.log(value);
                listData();
                // console.log(dataPut);
                let arrayOfTrValuesJson = JSON.stringify(dataPut);
                // console.log(arrayOfTrValuesJson);
                makePutRequest("http://localhost:8088/api/securities", arrayOfTrValuesJson)
                .then(data => {
                    // console.log(data);
                    getTableData(data);                         
                    disableBlur();                              
                })
                .catch(() => {
                    console.log(data);
                });
            });
        });
    }, 3000);


    let uniqueId = null;
    function getUniqueName(prefix="") {
        if (!uniqueId) {
            uniqueId = (new Date()).getTime();
        }
        return (prefix || 'id') + (uniqueId++);
    }

// ================================================================================

    function disableBlur() {
        const td = document.querySelectorAll("td");
        td.forEach( t => {
            t.addEventListener("keydown", (event)=> {
                if(event.keyCode == 13) {
                    event.target.setAttribute("contentEditable",false);
                //    console.log(event.target);
                    setTimeout(function(){
                        event.target.setAttribute("contentEditable",true);
                    },1000);
                }
            });
        });
    }


// ========================================================================================================

    // Graph logic
    function grapth() {
        let ctx = document.querySelector('#myChart').getContext('2d');

        let dates = [];
        let values = [];
        let items = [];

        const tr = document.querySelectorAll("tbody tr");
        tr.forEach(tr => {
            let count = 0;
            const td = tr.querySelectorAll("tbody tr td");
            td.forEach(item => {
                if(count === 1) {
                    dates.push(item.textContent);
                }
                if(count === 3) {
                    values.push(item.textContent);
                }
                if(count === 2) {
                    items.push(item.textContent);
                }
                count++;
            });
        });
        
        let chart = new Chart(ctx, {    
            type: 'line',
            data: {
                labels: dates,
                datasets: [{
                    label: 'Линия',
                    backgroundColor: 'transparent',
                    borderColor: 'blue',
                    data: values
                }]
            },
            options: {
                title: {
                    display: true,
                    text: 'График зависимости стоимости инструментов по датам',
                    position: 'top',
                    fontSize: 16,
                    padding: 20
                },
                legend: {
                    display: true,
                    position: 'bottom',
                },
                plugins: {
                    datalabels: {
                        color: 'red',
                        labels: {
                            title: {
                                font: {
                                    weight: 'bold',
                                    size: 16
                                }
                            }
                        }
                    }
                }
            }
        });
    }
});

