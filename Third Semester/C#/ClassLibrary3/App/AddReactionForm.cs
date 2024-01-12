﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using vaccinecalend;

namespace App
{
    public partial class addReactionForm : Form
    {
        public addReactionForm(Vaccination vaccination)
        {
            InitializeComponent();
            this.vaccination = vaccination;
        }
        private Vaccination vaccination;

        private void addReactionButton_Click(object sender, EventArgs e)
        {
            using (var context = new VaccineCalendarContext())
            {
                try
                {
                    string reaction = reactionTextBox.Text;
                    ReactionOnVaccination reactionOnVaccination = new ReactionOnVaccination();
                    reactionOnVaccination.DescriptionOfReaction = reaction;
                    reactionOnVaccination.DateOfReaction = DateTime.UtcNow;
                    reactionOnVaccination.VaccinationId = vaccination.VaccinationId;
                    reactionOnVaccination.ReactionId = Guid.NewGuid();
                    DBService service = new DBService(context);
                    service.AddReactionOnVaccination(reactionOnVaccination);
                    MessageBox.Show("Реакция успешно добавлена!", "Успех", MessageBoxButtons.OK, MessageBoxIcon.Information);

                    this.Close();
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Произошла ошибка: {ex.Message}", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }
    }
}
